db.getCollection('review').aggregate([
    {$group: 
        {
            _id: '$business_id',
            avgStars: {$avg: '$stars'}
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
    {$project: {'name': '$business_data.name', 'avgStars': '$avgStars'}},
    {$match: {'avgStars': {$gte: 4.0}}},
    {$sort: {avgStars: 1, name: 1}}
])