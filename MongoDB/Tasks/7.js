// product
db.createCollection("product", {
   validator: {
      $jsonSchema: {
         bsonType: "object",
         required: [ "name", "product_id", "category", "manufacturer", "price", "unitsInStock" ],
         properties: {
            name: {
               bsonType: "string",
               description: "Must be a string and is required"
            },
            product_id: {
               bsonType: "string",
               description: "Must be a string and is required"
            },
            category: {
               bsonType: "string",
               description: "Must be a string and is required"
            },
            manufacturer: {
               bsonType: "string",
               description: "Must be a string and is required"
            },
            price: {
               bsonType: "double",
               description: "Must be a double and is required"
            },
            unitsInStock: {
               bsonType: "int",
               description: "Must be a int and is required"
            }
         }
      }
   }
})

var insertProduct = function(name, category, manufacturer, price, unitsInStock) {
    prodID = new ObjectId().valueOf()
    db.product.insert({
        product_id: prodID,
        category: category,
        name: name,
        manufacturer: manufacturer,
        price: price,
        unitsInStock: NumberInt(unitsInStock)
    })
}

insertProduct("The Wicther 3: Wild Hunt PC", "games", "CD Project Red", 110.0, 50)

// customer
db.createCollection("customer", {
   validator: {
      $jsonSchema: {
         bsonType: "object",
         required: [ "customer_id", "name", "phone", "email", "address", "orders" ],
         properties: {
            customer_id: {
                bsonType: "string",
                description: "Must be a string and is required"
            },
            name: {
               bsonType: "object",
               required: [ "firstname", "lastname" ],
               properties: {
                    firstname: {
                        bsonType: "string",
                        description: "Must be a string and is required"
                    },
                    lastname: {
                        bsonType: "string",
                        description: "Must be a string and is required"
                    }
               }
            },
            phone: {
               bsonType: "string",
               description: "Must be a string and is required"
            },
            email: {
               bsonType: "string",
               description: "Must be a string and is required"
            },
            address: {
               bsonType: [ "object" ],
               required: [ "country", "city", "postalCode", "street", "buildingNumber" ],
               properties: {
                    country: {
                        bsonType: "string",
                        description: "Must be a string and is required"
                    },
                    city: {
                        bsonType: "string",
                        description: "Must be a string and is required"
                    },
                    postalCode: {
                        bsonType: "string",
                        description: "Must be a string and is required"
                    },
                    street: {
                        bsonType: "string",
                        description: "Must be a string and is required"
                    },
                    buildingNumber: {
                        bsonType: "string",
                        description: "Must be a string and is required"
                    },
               }
            },
           orders: {
                bsonType: "array",
                description: "Must be an arry and is required (can be empty)"
           } 
         }
      }
   }
})

var insertCustomer = function
(firstname, lastname, phone, email, country, city, postalCode, street, buildingNumber) {
    custID = new ObjectId().valueOf()    
    db.customer.insert({
        customer_id: custID,
        name:
        {
            firstname: firstname,
            lastname: lastname
        },
        phone: phone,
        email: email,
        address:
        {
            country: country,
            city: city,
            postalCode: postalCode,
            street: street,
            buildingNumber: buildingNumber
        },
        orders: []
    })
}

insertCustomer('Jan', 'Kowalski', '123456789', 'kowal@o2.com', 'Polska', 'Sosnowiec', 
'21-370', 'D³uga', '5A')

// order
db.createCollection("order", {
   validator: {
      $jsonSchema: {
         bsonType: "object",
         required: [ "order_id", "customer_id", "name", "date", "prodcut_id", "prodcut_name",
          "quantity", "price" ],
         properties: {
            order_id: {
               bsonType: "string",
               description: "Must be a string and is required"
            },
            customer_id: {
               bsonType: "string",
               description: "Must be a string and is required"
            },
            name: {
               bsonType: "string",
               description: "Must be a string and is required"
            },
            date: {
               bsonType: [ "string" ],
               description: "Must be a string and is required"
            },
            prodcut_id: {
               bsonType: "string",
               description: "Must be a string and is required"
            },
            prodcut_name: {
               bsonType: "string",
               description: "Must be a string and is required"
            },
            quantity: {
               bsonType: "int",
               description: "Must be a int and is required"
            },
            price: {
               bsonType: "double",
               description: "Must be a double and is required"
            }
         }
      }
   }
})

var addOrder = function(productID, customerID, quantity) {
    crs = db.product.find({product_id: productID})
    prod = crs.next()
    if(quantity > prod.unitsInStock)
        throw "Not enough units in stock"
    
    crs2 = db.customer.find({customer_id: customerID})
    cust = crs2.next()
    
    ordID = new ObjectId().valueOf()
    
    db.order.insert({
        order_id: ordID,
        customer_id: customerID,
        name: cust.name.firstname + ' ' + cust.name.lastname,
        date: new Date().toISOString().slice(0, 16).replace('T', ' '),
        prodcut_id: productID,
        prodcut_name: prod.name,
        quantity: NumberInt(quantity),
        price: quantity * prod.price
    })
    
    db.customer.update(
        {customer_id: customerID},
        {$addToSet: {orders: ordID}}
    )
        
    db.product.update(
        {product_id: productID},
        {$inc: {unitsInStock: NumberInt(-quantity)}}
    )
}

addOrder('5e91f5d1f34b21f5ca8deac1', '5e91f78ef34b21f5ca8dead1', 5)