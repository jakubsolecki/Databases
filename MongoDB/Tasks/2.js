function createReview(user_id, stars, date, text, business_id){
    if (![1, 2, 3, 4, 5].includes(stars)){
        throw 'Wrong value! Star must be an integer from range [1, 5]';
    }
    db.review.insert({
        votes: {
            funny: 0,
            useful: 0,
            cool: 0
        },
        user_id: user_id,
        review_id: new ObjectId(),
        stars: stars,
        date: date,
        text: text,
        type: 'review',
        business_id: business_id
    })
}

createReview('userID', 5.0, '2020-04-08', 'Best restaurant on the planet!', 'businessID')