from etcd_client import EtcdClient
import json


class DistributedCache(object):
    def __init__(self, config_path, key_prefix, logger):
        self.etcd_client = EtcdClient(config_path, key_prefix, logger, 240)  # reconnect every 4 minutes
        self.logger = logger
        self.key_prefix = key_prefix
        self.cache = {}
        self.etcd_client.add_watch_prefix_callback(self.key_prefix, self.prefix_callback)

    @staticmethod
    def prefix_callback(msg):
        print(msg)

    def get(self, key: str, use_cache: bool = True) -> str:
        if use_cache and self.cache.get(key):
            return self.cache.get(key)
        else:
            key_path = self.key_prefix + "/" + key
            value = self.etcd_client.get(key_path)[0]
            if not value:
                return value
            json_data = json.loads(value.decode('utf-8'))
            self.cache[key] = json_data
            return json_data

    def put(self, key: str, value: str):
        self.etcd_client.put(self.key_prefix + "/" + key, json.dumps(value))

    def delete(self, key: str):
        self.etcd_client.delete(self.key_prefix + "/" + key)
