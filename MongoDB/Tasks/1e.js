db.getCollection('tip').aggregate([
    {$match: {'date': /2012/}},
    {$group: 
        {
            _id: '$business_id',
            count: {$sum: 1}
        }
    },
    {
        $lookup:
        {
            from: 'business',
            localField: '_id',
            foreignField: 'business_id',
            as: 'business_data'
        }
    },
    {$unwind: '$business_data'},
    {$project: {'name': '$business_data.name', 'count': '$count'}},
    {$sort: {count: 1, name: 1}}
])