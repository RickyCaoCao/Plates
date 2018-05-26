# enghack2018

### Backend API

**URL:** `https://rickycaocao.lib.id/backend/plates`

**Required Params:**
 - `latitude`
 - `longitude`

**Optional Params (and defaults):**
 - `category = 'food'`
    - food category, eg. 'mexican'
 - `count = 20`
    - number of plates to return
 - `offset = 0`
    - for pagination

**Return:** Array of objects

``` js
[
    {
        "name": "R&R Burger Place",
        "food_img": "https://upload.wikimedia.org/wikipedia/en/a/a9/Example.jpg",
        "type": "burgers",
        "rating": 5,
        "price": "$$"
    }
]
```

