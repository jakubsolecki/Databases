db.getCollection('review').find({
        date: {$gte: "2011-01-01"}
}).count()