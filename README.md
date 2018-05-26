# enghack2018

### Commands

`lib .plates --latitude 40.749319 --longitude -73.986089`





### Backend API

**Example URL**: `https://rickycaocao.lib.id/backend/plates?latitude=40.749319&longitude= -73.986089`

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
        "price": "$$",
        "offset": 1
    }
]
```

