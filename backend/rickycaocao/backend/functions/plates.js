require('dotenv').config();
const request = require('request');
const qs = require('qs');
var MongoClient = require('mongodb').MongoClient
  , assert = require('assert');

const YELP_SEARCH_URL = 'https://api.yelp.com/v3/businesses/search?';
const YELP_API_KEY = process.env.YELP_API_KEY;
const API_HEADERS = {
  'Authorization': 'Bearer ' + YELP_API_KEY
}

// DB Connection URL
const DB_URL = process.env.MONGO_URL;

const DEFAULT_IMG_URL = 'https://img.freepik.com/free-photo/' +
  'cutlery-overhead-wooden-dining-food_1203-6082.jpg?size=338&ext=jpg';

/**
* Returns plates
* @param {string} category the type of food to search for
* @param {float} latitude the latitude of user
* @param {float} longitude the longitude of user
* @param {integer} count the number of plates to return
* @param {integer} offset for pagination
* @param {boolean} use_db to demonstrate that you can use our own DB
* @returns {array}
*/

module.exports = (category = 'food', latitude, 
  longitude, count = 20, offset = 0, use_db = false, context, callback) => {

  if(use_db){
    console.log("Using DB\n");
    let query = {
      'category': category,
    };

    query_db({
      'category': category,
      'offset': {
        $gt: offset-1,
        $lt: count+offset
      }
    }, (plates) => {
      console.log(plates);
      callback(null, plates);
    });
  }
  else{
    //P2 TODO: Pricing Query
    let query = {
        'term': category,
        'latitude': latitude,
        'longitude': longitude,
        'limit': count,
        'offset': offset
      };

    query_yelp(query, (plates) => {
      callback(null, plates);
    });
  }
}

var query_yelp = function(query, callback) {
  let querystring = qs.stringify(query);

  const options = {
    'method': 'GET',
    'url': YELP_SEARCH_URL + querystring,
    'headers': API_HEADERS,
    json: true
  }

  request(options, (err, res, body) => {
    if (err) {
      callback(null, 
        {
          "error": {
            "message": "Yelp API returned an error."
          }
        }
      );
    }

    //TODO: Add a DB, populate with Yelp data
    //TODO: Fix Offset
    //TODO: STDLIB API Status Codes
    callback(body.businesses.map(function(plate, index) {
      return {
        'name': plate.name || 'NO DATA',
        'food_img': plate.image_url || DEFAULT_IMG_URL,
        'type': plate.categories[0].title || 'NO DATA',
        'rating': plate.rating || 'NO DATA',
        'price': plate.price || 'NO DATA',
        'offset': query.offset + index
      }
    }));
  });
}

var query_db = function(query, callback) {
  // Use connect method to connect to the server
  MongoClient.connect(DB_URL, function(err, client) {
    assert.equal(null, err);
    console.log("Connected successfully to server");
    

    findDocuments(client.db('test'), query, function(result) {
      client.close();
      callback(result);
    });
  });
}

var findDocuments = function(db, query, callback) {
  // Get the documents collection
  var collection = db.collection('plates');

  collection.find(query).toArray(
    function(err, result) {
      assert.equal(err, null);
      console.log('Retrieved plates from DB.\n');

      callback(result.map(function(plate, index) {
        return {
          'name': plate.name || 'NO DATA',
          'food_img': plate.food_img || DEFAULT_IMG_URL,
          'type': plate.type || 'NO DATA',
          'rating': plate.rating || 'NO DATA',
          'price': plate.price || 'NO DATA',
          'offset': plate.offset || 0
        }
      }));
    }
  );
}
