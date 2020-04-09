function findBusiness(category){
    return db.getCollection('business').find({
        categories: category
    })
}

findBusiness('Chinese')