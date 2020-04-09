import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

public class MongoLab {
    private MongoClient mongoClient;
    private MongoDatabase db;

    public MongoLab() throws UnknownHostException {
        mongoClient = new MongoClient("localhost" , 27017);
        db = mongoClient.getDatabase("JakubSoleckiWt11_15A");
    }

    private void showCollections(){
        for(String name : db.listCollectionNames()){
            System.out.println("colname: "+name);
        }
    }

    private long zad6_5a(){
        MongoCollection<Document> businessColl = db.getCollection("business");
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("stars", 5);
        return businessColl.countDocuments(whereQuery);

    }

    private void zad6_5b() {
        MongoCollection<Document> businessColl = db.getCollection("business");
        AggregateIterable agg = businessColl.aggregate(Arrays.asList(
                        Aggregates.match(Filters.eq("categories", "Restaurants")),
                        Aggregates.group("$city", Accumulators.sum("count", 1)),
                        Aggregates.sort((Sorts.descending("count")))
                )
        );

        for(Object res : agg) {
            System.out.println((res));
        }
    }

    private void zad6_5c() {
        MongoCollection<Document> businessColl = db.getCollection("business");
        AggregateIterable agg = businessColl.aggregate(Arrays.asList(
                Aggregates.match(Filters.and(
                        Filters.eq("categories", "Hotels"),
                        Filters.eq("attributes.Wi-Fi", "free"),
                        Filters.gte("stars", 4.5)
                        )),
                Aggregates.group("$state", Accumulators.sum("count", 1)),
                Aggregates.sort(Sorts.descending("count"))
                )
        );

        for(Object res : agg) {
            System.out.println((res));
        }
        
    }

//    private String zad7(){
//        MongoCollection reviews = db.getCollection("review");
//        Object res = Objects.requireNonNull(reviews.aggregate(Arrays.asList(
//                Aggregates.match(Filters.gte("stars", 4.5)),
//                Aggregates.group("$user_id", Accumulators.sum("count", 1)),
//                Aggregates.sort(Sorts.descending("count"))
//        )).first()).get("_id");
//
//        return Objects.requireNonNull(db.getCollection("user").find(Filters.eq("user_id",
//                res.toString())).first()).get("name").toString();
//    }


    private List<String> zad8() {
        MongoCollection<Document> revs = db.getCollection("review");
        List<String> count = new ArrayList<>();

        count.add("funny: " + revs.countDocuments(Filters.gt("votes.funny", 0)));
        count.add("cool: " + revs.countDocuments(Filters.gt("votes.cool", 0)));
        count.add("useful: " + revs.countDocuments(Filters.gt("votes.useful", 0)));

        return count;
    }


    public static void main(String[] args) throws UnknownHostException {
        MongoLab mongoLab = new MongoLab();
        mongoLab.showCollections();
        System.out.println(mongoLab.zad6_5a());
        System.out.println("----------------------------------");
        mongoLab.zad6_5b();
        System.out.println("----------------------------------");
        mongoLab.zad6_5c();
        System.out.println("----------------------------------");
//        System.out.println(mongoLab.zad7());
//        System.out.println("----------------------------------");
        System.out.println(mongoLab.zad8());


    }

}
