## ETCD LAB 

A distributed, reliable key-value store for the most critical data of a distributed system.  
Homepage: https://etcd.io/

Key features:

- Simple: well-defined, user-facing API (gRPC)
- Secure: automatic TLS with optional client cert authentication
- Fast: benchmarked 10,000 writes/sec
- Reliable: properly distributed using Raft


There are two major use cases: concurrency control in the distributed system and application configuration store. For example, CoreOS Container Linux uses etcd to achieve a global semaphore to avoid that all nodes in the cluster rebooting at the same time. Also, Kubernetes use etcd for their configuration store.

During this lab we will be using etcd3 python client.  
Homepage: https://pypi.org/project/etcd3/

Etcd credentials are shared on the slack channel: ...

Please copy & paste them into the cell below:

```python
etcdCreds = {...}
```
```
!pip install etcd3
```
### How to connect to etcd using certyficate (part 1: prepare file with certificate)

```python
import base64
import tempfile

etcdHost = etcdCreds["connection"]["grpc"]["hosts"][0]["hostname"]
etcdPort = etcdCreds["connection"]["grpc"]["hosts"][0]["port"]
etcdUser = etcdCreds["connection"]["grpc"]["authentication"]["username"]
etcdPasswd = etcdCreds["connection"]["grpc"]["authentication"]["password"]
etcdCertBase64 = etcdCreds["connection"]["grpc"]["certificate"]["certificate_base64"]
                           
etcdCertDecoded = base64.b64decode(etcdCertBase64)
etcdCertPath = "{}/{}.cert".format(tempfile.gettempdir(), etcdUser)
                           
with open(etcdCertPath, 'wb') as f:
    f.write(etcdCertDecoded)

print(etcdCertPath)
```
```
/home/dsxuser/.tmp/ibm_cloud_f59f3a7b_7578_4cf8_ba20_6df3b352ab46.cert
```

### Short Lab description

During the lab we will simulate system that keeps track of logged users
- All users will be stored under parent key (path): /logged_users
- Each user will be represented by key value pair
    - key /logged_users/name_of_the_user
    - value hostname of the machine (e.g. name_of_the_user-hostname)
    
### How to connect to etcd using certyficate (part 2: create client)
```python
import etcd3

etcd = etcd3.client(
    host=etcdHost,
    port=etcdPort,
    user=etcdUser,
    password=etcdPasswd,
    ca_cert=etcdCertPath
)

cfgRoot='/logged_users'
```

### Task1 : Fetch username and hostname

Define two variables
- username name of the logged user (tip: use getpass library)
- hostname hostname of your mcomputer (tip: use socket library)

```python
import getpass
import socket

# username = getpass.getuser()  # You can put your name here, while this code is run in the container and user name would be same for all students
username = 'solecki'
hostname = socket.gethostname()

userKey='{}/{}'.format(cfgRoot, username)
userKey, '->', hostname
```
```
('/logged_users/solecki',
 '->',
 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk')
 ```

### Task2 : Register number of users 

etcd3 api: https://python-etcd3.readthedocs.io/en/latest/usage.html
For all names in table fixedUsers register the appropriate key value pairs
```python
fixedUsers = [
    'Adam',
    'Borys',
    'Cezary',
    'Damian',
    'Emil',
    'Filip',
    'Gustaw',
    'Henryk',
    'Ignacy',
    'Jacek',
    'Kamil',
    'Leon',
    'Marek',
    'Norbert',
    'Oskar',
    'Patryk',
    'Rafał',
    'Stefan',
    'Tadeusz'
]

for user in fixedUsers:
    etcd.put(f'{cfgRoot}/{username}/{user}', f'{hostname}')

for user in fixedUsers:
    value, metadata = etcd.get(f'{cfgRoot}/{username}/{user}')
    print(metadata.key.decode('utf-8'))
    print(value.decode('utf-8'), '\n')
```
```
/logged_users/solecki/Adam
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Borys
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Cezary
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Damian
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Emil
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Filip
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Gustaw
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Henryk
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Ignacy
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Jacek
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Kamil
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Leon
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Marek
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Norbert
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Oskar
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Patryk
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Rafał
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Stefan
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk 

/logged_users/solecki/Tadeusz
notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk
```

### Task3: List all users

etcd3 api: https://python-etcd3.readthedocs.io/en/latest/usage.html
List all registered user (tip: use common prefix)
```python
results = etcd.get_prefix(f'{cfgRoot}')
{m.key.decode('utf-8') : v.decode('utf-8') for v, m in results}
```
```
{'/logged_users': '/logged_users/Tadeusz - Registered',
 '/logged_users/Adam': 'Adam-hostname',
 '/logged_users/Borys': 'Borys-hostname',
 '/logged_users/Cezary': 'Cezary-hostname',
 '/logged_users/Damian': 'Damian-hostname',
 '/logged_users/Emil': 'Emil-hostname',
 '/logged_users/Filip': 'Filip-hostname',
 '/logged_users/Gustaw': 'Gustaw-hostname',
 '/logged_users/Henryk': 'Henryk-hostname',
 '/logged_users/Ignacy': 'Ignacy-hostname',
 '/logged_users/Jacek': 'Jacek-hostname',
 '/logged_users/Kamil': 'Kamil-hostname',
 '/logged_users/Kocimski/Adam': 'Adam',
 '/logged_users/Kocimski/Borys': 'Borys',
 '/logged_users/Kocimski/Cezary': 'Cezary',
 '/logged_users/Kocimski/Damian': 'Damian',
 '/logged_users/Kocimski/Emil': 'Emil',
 '/logged_users/Kocimski/Filip': 'Filip',
 '/logged_users/Kocimski/Gustaw': 'Gustaw',
 '/logged_users/Kocimski/Henryk': 'Henryk',
 '/logged_users/Kocimski/Ignacy': 'Ignacy',
 '/logged_users/Kocimski/Jacek': 'Kazimierz',
 '/logged_users/Kocimski/Kamil': 'Kamil',
 '/logged_users/Kocimski/Leon': 'Leon',
 '/logged_users/Kocimski/Marek': 'Marek',
 '/logged_users/Kocimski/Norbert': 'Norbert',
 '/logged_users/Kocimski/Oskar': 'Oskar',
 '/logged_users/Kocimski/Patryk': 'Patryk',
 '/logged_users/Kocimski/Rafał': 'Rafał',
 '/logged_users/Kocimski/Stefan': 'Stefan',
 '/logged_users/Kocimski/Tadeusz': 'Tadeusz',
 '/logged_users/Leon': 'Leon-hostname',
 '/logged_users/Marek': 'Marek-hostname',
 '/logged_users/Norbert': 'Norbert-hostname',
 '/logged_users/NotWatchedKey': 'value first',
 '/logged_users/Oskar': 'Oskar-hostname',
 '/logged_users/Patryk': 'Patryk-hostname',
 '/logged_users/Rafał': 'Rafał-hostname',
 '/logged_users/Stefan': 'Stefan-hostname',
 '/logged_users/Tadeusz': 'Tadeusz-hostname',
 '/logged_users/WatchedKey': 'value second',
 '/logged_users/anything': 'value',
 '/logged_users/being_watched': 'value',
 '/logged_users/leaseval': 'leaseval',
 '/logged_users/solecki': 'Tadeusz-notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Adam': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Borys': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Cezary': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Damian': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Emil': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Filip': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Gustaw': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Henryk': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Ignacy': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Jacek': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Kamil': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Leon': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Marek': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Norbert': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Oskar': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Patryk': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Rafał': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Stefan': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Tadeusz': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/logged_lease': 'I exist!',
 '/logged_users/status': 'Success',
 '/logged_users/tmp_user': 'tmp_user-hostname'}
```

### Task 4 : Same as Task2, but use transaction

etcd3 api: https://python-etcd3.readthedocs.io/en/latest/usage.html
For all names in table fixedUsers register the appropriate key value pairs, use transaction to make it a single request 
(Have you noticed any difference in execution time?)
```python
etcd.transaction(
    compare=[],
    success=[
        etcd.transactions.put(f'{cfgRoot}/{username}/{user}', f'{user}') for user in fixedUsers
    ],
    failure=[]
)

results = etcd.get_prefix(f'{cfgRoot}/{username}/')
{m.key.decode('utf-8') : v.decode('utf-8') for v, m in results}
```
```
{'/logged_users/solecki/Adam': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Borys': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Cezary': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Damian': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Emil': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Filip': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Gustaw': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Henryk': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Ignacy': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Jacek': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Kamil': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Leon': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Marek': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Norbert': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Oskar': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Patryk': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Rafał': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Stefan': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk',
 '/logged_users/solecki/Tadeusz': 'notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk'}
```

### Task 5 : Get single key (e.g. status of transaction)
etcd3 api: https://python-etcd3.readthedocs.io/en/latest/usage.html
Check the key you are modifying in on-failure handler in previous task
```python
for i in etcd.get_prefix('/tmp/failure'):
    print(i)
```
```
(b'condtion failed', <etcd3.client.KVMetadata object at 0x7f22441f0d68>)
```

### Task 6 : Get range of Keys (Emil -> Oskar) 

etcd3 api: https://python-etcd3.readthedocs.io/en/latest/usage.html

- Get range of keys
- Is it inclusive / exclusive?
- Sort the resposne descending
```python
results = etcd.get_range(f'{cfgRoot}/{username}/Emil', f'{cfgRoot}/{username}/Oskar', sort_order='descend')
for res in results:
#     print(res)
    print(res[1].key.decode('utf-8') + ' - ' + res[0].decode('utf-8'))
```
```
/logged_users/solecki/Norbert - notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk
/logged_users/solecki/Marek - notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk
/logged_users/solecki/Leon - notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk
/logged_users/solecki/Kamil - notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk
/logged_users/solecki/Jacek - notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk
/logged_users/solecki/Ignacy - notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk
/logged_users/solecki/Henryk - notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk
/logged_users/solecki/Gustaw - notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk
/logged_users/solecki/Filip - notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk
/logged_users/solecki/Emil - notebook-condafree1py362847d525672542009895af0d8d60e2f2-79xg5sk
```

### Task 7: Atomic Replace

etcd3 api: https://python-etcd3.readthedocs.io/en/latest/usage.html
Do it a few times, check if value has been replaced depending on condition
```python
replace = lambda old, new: etcd.replace(f'{cfgRoot}/{username}/Emil', old, new)
first_key = hostname
em = 'Emil'
not_em = 'Not Emil'
new_key = 'new key'

print(get)

print(f'Repalce status: {replace(first_key, not_em)}, Key after operation:', etcd.get(f'{cfgRoot}/{username}/Emil')[0].decode('utf-8'))

print(f'Repalce status: {replace(not_em, em)}, Key after operation:', etcd.get(f'{cfgRoot}/{username}/Emil')[0].decode('utf-8'))

print(f'Repalce status: {replace(not_em, em)}, Key after operation:', etcd.get(f'{cfgRoot}/{username}/Emil')[0].decode('utf-8'))

print(f'Repalce status: {replace(new_key, not_em)}, Key after operation:', etcd.get(f'{cfgRoot}/{username}/Emil')[0].decode('utf-8'))
```
```
<function <lambda> at 0x7f2244289158>
Repalce status: True, Key after operation: Not Emil
Repalce status: True, Key after operation: Emil
Repalce status: False, Key after operation: Emil
Repalce status: False, Key after operation: Emil
```

### Task 8 : Create lease - use it to create expiring key

etcd3 api: https://python-etcd3.readthedocs.io/en/latest/usage.html
You can create a key that will be for limited time
add user that will expire after a few seconds

Tip: Use lease
```python
import time

lease = etcd.lease(3)
etcd.put(f'{cfgRoot}/{username}/TMP', 'Temporary value', lease=lease)
value, meta = etcd.get(f'{cfgRoot}/{username}/TMP')
print(meta.key.decode('utf-8') + " : " + value.decode('utf-8'))
time.sleep(4)
print('Attempting to abtain leased key: ', str(etcd.get(f'{cfgRoot}/{username}/TMP')))
```
```
/logged_users/solecki/TMP : Temporary value
Attempting to abtain leased key:  (None, None)
```

### Task 9 : Create key that will expire after you close the connection to etcd

Tip: use threading library to refresh your lease
```python
import threading

lease = etcd.lease(ttl=5)

def refresh_lease():
    while True:
        lease.refresh()
        time.sleep(1)

key=f'{cfgRoot}/{username}/logged_lease'
val='I exist!'


etcd.put(key, val, lease=lease)

t = threading.Thread(target=refresh_lease)
t.start()

print(etcd.get(key)[0].decode('utf-8'))
time.sleep(15)
print(etcd.get(key)[0].decode('utf-8'))
```
```
I exist!
I exist!
```

### Task 10: Use lock to protect section of code

etcd3 api: https://python-etcd3.readthedocs.io/en/latest/usage.html
```python 
with etcd.lock('lock-2', ttl=10) as lock:
    print('a')
    print(f'Is acquaired? {lock.is_acquired()}')
    lock.acquire()
    print('b')
    time.sleep(3)
    print('c')
    lock.release()
```
```
a
Is acquaired? True
b
c
```

### Task 11 Watch key

etcd3 api: https://python-etcd3.readthedocs.io/en/latest/usage.html
This cell will lock this notebook on waiting  
After running it create a new notebook and try to add new user
```python
def etcd_call(cb):
    print(cb)

etcd.put('/test/test', 'test')
etcd.add_watch_callback(key='/test/test', callback=etcd_call)
```
```
1
<etcd3.watch.WatchResponse object at 0x7f22441d1828>
<etcd3.watch.WatchResponse object at 0x7f2244239128>
<etcd3.watch.WatchResponse object at 0x7f2244239128>
```
