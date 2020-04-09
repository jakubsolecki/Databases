function modifyUserName(id, newName){
    db.user.update(
        {user_id: id},
        {$set: {name: newName}}
    )
}

modifyUserName('qtrmBGNqCvupHMHL_bKFgQ', 'Bruce Lee')
db.user.find({user_id: 'qtrmBGNqCvupHMHL_bKFgQ'})