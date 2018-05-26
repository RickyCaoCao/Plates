require('dotenv').config();
const request = require('request');
const qs = require('qs');

const YELP_SEARCH_URL = 'https://api.yelp.com/v3/businesses/search?';
const YELP_API_KEY = process.env.YELP_API_KEY;
const API_HEADERS = {
  'Authorization': 'Bearer ' + YELP_API_KEY
}

const DEFAULT_IMG_URL = 'https://img.freepik.com/free-photo/' +
  'cutlery-overhead-wooden-dining-food_1203-6082.jpg?size=338&ext=jpg';

/**
* Returns plates
* @param {string} category the type of food to search for
* @param {float} latitude the latitude of user
* @param {float} longitude the longitude of user
* @param {integer} count the number of plates to return
* @param {integer} offset for pagination
* @returns {array}
*/
module.exports = (category = 'food', latitude, 
  longitude, count = 20, offset = 0, context, callback) => {

  //P2: Pricing Query
  let query = qs.stringify(
    {
      'term': category,
      'latitude': latitude,
      'longitude': longitude,
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
    if (err) {
      callback(null, 
        {
          "error": {
            "message": "Yelp API returned an error."
          }
        }
      );
    }

    //TODO: STDLIB API Status Codes
    callback(null, body['businesses'].map(function(place) {
      return {
        'name': place.name || 'NO DATA',
        'food_img': place.image_url || DEFAULT_IMG_URL,
        'type': place.categories[0].title || 'NO DATA',
        'rating': place.rating || 'NO DATA',
        'price': place.price || 'NO DATA',
        'offset': offset + count
      }
    }));
  });
}
