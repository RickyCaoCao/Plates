require('dotenv').config();
const request = require('request');
const qs = require('qs');

const YELP_SEARCH_URL = 'https://api.yelp.com/v3/businesses/search?';
const YELP_API_KEY = process.env.YELP_API_KEY;
const API_HEADERS = {
  'Authorization': 'Bearer ' + YELP_API_KEY
};

var MongoClient = require('mongodb').MongoClient
  , assert = require('assert');

// Connection URL
const DB_URL = process.env.DB_URL;

const COUNT = 50;

// Use connect method to connect to the server
MongoClient.connect(DB_URL, function(err, client) {
  assert.equal(null, err);
  console.log("Connected successfully to server");

  // drop_db(client.db('test'), 'plates', function () {
  //   client.close();
  // });

  insertDocuments(client.db('test'), function() {
    client.close();
  });
});

var populateDB = function(count = 50, callback) {
  let category = 'food';
  let offset = 0;

  let query = qs.stringify(
    {
      'term': category,
      'latitude': 40.749319, 
      'longitude': -73.986089,
      'limit': count,
      'offset': offset
    }
  );

  const options = {
    'method': 'GET',
    'url': YELP_SEARCH_URL + query,
    'headers': API_HEADERS,
    json: true
  }

  request(options, (err, res, body) => {
    if (err) {console.error("ERROR IN SCRIPT")}

    callback(body.businesses.map(function(place, index) {
      return {
        'category': category,
        'name': place.name || 'NO DATA',
        'food_img': place.image_url || DEFAULT_IMG_URL,
        'type': place.categories[0].title || 'NO DATA',
        'rating': place.rating || 'NO DATA',
        'price': place.price || 'NO DATA',
        'offset': offset + index
      }
    }));
  });
}

var insertDocuments = function(db, callback) {
  // Get the documents collection
  var collection = db.collection('plates');
  var count = COUNT;

  // Insert some documents
  populateDB(count, (arr) => {
    collection.insertMany(arr,
      function(err, result) {
        assert.equal(err, null);
        assert.equal(count, result.result.n);
        assert.equal(count, result.ops.length);
        console.log("Inserted 20 documents into the collection");
        callback(result);
      });
  });
}

var drop_db = function(db, collection_name, callback) {
  db.collection(collection_name).drop(callback=callback)
}