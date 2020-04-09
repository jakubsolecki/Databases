import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.net.UnknownHostException;
import java.util.*;

import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Accumulators.sum;

public class MongoZad {
    private MongoClient mongoClient;
    private MongoDatabase db;

    public MongoZad() throws UnknownHostException {
        mongoClient = new MongoClient("localhost" , 27017);
        db = mongoClient.getDatabase("JakubSoleckiWt11_15A");
    }

    private void zad_6_1_a() {
        List<String> res = db.getCollection("business").distinct("city", String.class).into(new ArrayList<>());
        Collections.sort(res);

        for(String s: res)
            System.out.println(s);
    }

    private void zad_6_1_b() {
        MongoCollection<Document> review = db.getCollection("review");
        System.out.println(review.countDocuments(Filters.gte("date", "2011-01-01")));
    }

    private void zad_6_1_c() {
        List<Document> res = db.getCollection("business")
                .find(new Document("open", false))
                .projection(
                        new Document("name", 1)
                        .append("full_address", 1)
                        .append("stars", 1)
                ).into(new ArrayList<Document>());

        for(Document doc: res)
            System.out.println(doc.toJson());
    }

    private void zad_6_1_d() {
        MongoCollection<Document> user = db.getCollection("user");

        DBObject clause1 = new BasicDBObject("votes.funny", 0);
        DBObject clause2 = new BasicDBObject("votes.useful", 0);
        BasicDBList or = new BasicDBList();
        or.add(clause1);
        or.add(clause2);
        BasicDBObject query = new BasicDBObject("$or", or);

        FindIterable<Document> res = user.find(query).sort(new Document("name", -1));
        for(Document doc: res)
            System.out.println(doc.toJson());
    }

    private void zad_6_1_e() {
        MongoCollection<Document> tip = db.getCollection("tip");

        AggregateIterable<Document> agg = tip.aggregate(Arrays.asList(
                Aggregates.match(Filters.regex("date", "2012")),
                Aggregates.group("$business_id", sum("count", 1)),
                Aggregates.lookup("business", "_id", "business_id", "business_data"),
                Aggregates.unwind("$business_data"),
                Aggregates.project(
                        new Document("name", "$business_data.name")
                        .append("count", "$count")
                ),
                Aggregates.sort(Sorts.ascending("count"))
        ));

        for(Document doc: agg)
            System.out.println(doc.toJson());
    }

    private void zad_6_1_f() {
        MongoCollection<Document> tip = db.getCollection("review");

        AggregateIterable<Document> agg = tip.aggregate(Arrays.asList(
                Aggregates.group("$business_id", avg("avgStars", "$stars")),
                Aggregates.lookup("business", "_id", "business_id", "business_data"),
                Aggregates.unwind("$business_data"),
                Aggregates.project(
                        new Document("name", "$business_data.name")
                                .append("avgStars", "$avgStars")
                ),
                Aggregates.match(Filters.gte("avgStars", 4.0)),
                Aggregates.sort(Sorts.ascending("avgStars"))
        ));

        for(Document doc: agg)
            System.out.println(doc.toJson());
    }

    private void zad_6_1_g() {
        db.getCollection("business").deleteMany(Filters.eq("stars", 2));
    }

    public static void main(String[] args) throws UnknownHostException {
        MongoZad mongoZad = new MongoZad();
        mongoZad.zad_6_1_a();
        mongoZad.zad_6_1_b();
        mongoZad.zad_6_1_c();
        mongoZad.zad_6_1_d();
        mongoZad.zad_6_1_e();
        mongoZad.zad_6_1_f();
        mongoZad.zad_6_1_g();

    }
}
