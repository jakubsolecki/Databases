db.tip.mapReduce(
    function() { emit(this.business_id, 1); },
    function(key, values) { return Array.sum(values); },
    {
        out: 'tips_per_business'
    }
);
    
db.tips_per_business.aggregate([
    {$group: 
        {
            _id: null,
            avgTips: {$avg: '$value'}
        }
    }
]);