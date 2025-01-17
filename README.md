# Food Delivery API

A backend web application written in Spring Boot with Java. You can use this to quickly set up an API web application.
I'll be adding new features and further optimizations in the near future.

```
You can find the frontend React app [here](https://github.com/Batch5-Food-Delivery/Batch5OJTFoodDelivery).
```

This app primarily consists of 3 types of users which are Customers, Restaurant owners, and Delivery drivers.
One more type is the Admin which we will discuss at the end. This web app allows you to manage all these types of users.

The app utilizes web tokens for security concerns. For most of the end points, you are required to include the token inside your request header. How do we include it in the header? Let's see how we can get the tokens first.

## Authentication

Same as other web applications, this app will give you the token as soon as you log in. Here are the api endpoints for creating an account and logging in.

`POST "/user/create"`

You need to include the account details in the request body like this.

```
{
"firstname": "string",
"lastname": "string",
"username": "string",
"email": "string",
"password": "string",
}
```

Upon a successful API call, the response will look like this:

```
{
"id": 0,
"firstname": "string",
"lastname": "string",
"username": "string",
"email": "string",
"enable": true,
"profile": "string",
"available": true,
"createdAt": "2025-01-13T17:08:47.500Z",
"updatedAt": "2025-01-13T17:08:47.500Z",
"cart": {
  "id": 0,
  "grandTotal": 0
  },
  "roles": [
  "string"
  ]
}
```

The roles will automatically include ROLE_USER on our first initialization.
Now, let's log in.

`POST "/user/login"`

```
request body: {
"username": "string",
"password": "string"
}
```

```
response:
{
token: "string",
user: {
  //same as above
  }
}
```

Now, we have access to our token. We can use it to access other endpoints in our application. You need to include the token in the header to access other endpoints.

## Customer endpoints

The newly created accounts are assigned as normal customers i.e, ROLE_USER. Customers are allowed to do many things such as browsing through various restaurants, menus, and foods. Customers can freely create orders to get the foods they want. Speaking of orders, the customers are also allowed to manage their addresses, so that they can use the most appropriate addresses in their orders.

### Restaurant

`G
ET "/restaurants/all"`

This endpoint takes no params or request body. It will return a list of all the restaurants.

```
[
  {
    "id": 0,
    "name": "string",
    "profile": "string",
    "available": true,
    "description": "string",
    "address": {
      "id": 0,
      "township": "string",
      "street": "string",
      "additionalDetails": "string"
    }
  }
]
```

It's important to note that this endpoint will not return the menus and foods that belong to that restaursnt. If that's what's you're interested in, skip to the menu section.

Notice there is a field called profile? This actually represents the name of the profile picture of the restaurant. We can use the endpoint below to get the picture of the restaurant.

`G
ET "/restaurant/image/{imageName}"
`

You can use it as an href value of an html img tag.

You can also search for restaurants.

`G
ET "/restaurant/search"
`

eg. ` G
ET"/restaurant/search?name=value"`

This is your good old search endpoint. This has a parameter called name. The results will be an array of restaurants, just like the `/all` endpoint.

If you want to fetch the details of only one restaurant. You can use this endpoint:

`GET "/restaurant/{resId}"`

response:

```
  {
    "id": 0,
    "name": "string",
    "profile": "string",
    "available": true,
    "description": "string",
    "address": {
      "id": 0,
      "township": "string",
      "street": "string",
      "additionalDetails": "string"
    }
  }
```

In a lot of cases, we need to check if the person fetching the details of the endpoint is the owner of the restaurant. For performance reasons, we don't include that information with the restaurant details. However, you can check that with our new endpoint.

`GET "/restaurant/{resId}/isOwner"`

You don't need to pass in additional parameters for this endpoint. The endpoint will use the token provided in the header to figure out if the user is the owner.

response: boolean

This only returns a boolean true or false.

### Menu

After getting the details of the restaurants. We can you each restaurant's id to fetch it's menus.

`GET "/menu/{restaurantId}"`

response:

```
[
  {
    "id": 0,
    "name": "string",
    "restaurant": {
      "id": 0,
      "name": "string",
      "profile": "string",
      "available": true,
      "description": "string",
      "address": {
        "id": 0,
        "township": "string",
        "street": "string",
        "additionalDetails": "string"
      }
    },
    "foods": [
      {
        "name": "string",
        "picture": "string",
        "price": 0,
        "description": "string",
        "category": "string",
        "discount": 0,
        "menu": {
          "id": 0,
        },
        "restaurant": {
          "id": 0,
        },
        "available": true,
        "id": 0
      }
    ]
  }
]
```

As you can see, the get menu route doesn't just give you the menu, it also returns the list of foods that belong to that menu.

### Address

Before getting to the order endpoints, we need to know how the customer can manage their addresses.

`POST "/newAddress"`

request body:

```
{
  "township": "string",
  "street": "string",
  "additionalDetails": "string"
}
```

Customer can use this to create a new address. This returns the new address with an id assigned by the application.

response :

```
{
  "id": 0,
  "township": "string",
  "street": "string",
  "additionalDetails": "string"
}
```

There is also another endpoint to fetch all addresses that belong to the currently logged in user.

`GET "/myAddresses"`

response:

```
[
  {
  "id": 0,
  "township": "string",
  "street": "string",
  "additionalDetails": "string"
  }
]
```

### Order

These are the endpoints the user can use to create or fetch orders.

#### Making an order at an restaurant

`POST "/order/create"`

request body:

```
{
  "restaurant": {
    "id": 0,
  },
  "destination": {
    "id": 0,
  },
  "items": [
    {
      "food": {
        "id": 0
      },
      "quantity": 0
    }
  ]
}
```

Compared to other request bodies, this one looks a little complicated. But we will break this down. To create an order, the customer will need the id of the `restaurant` and the id of one of their `addresses`. The `'destination'` in the request body refers to the customer's address. The `food` items they want to order are placed inside the array `items`. For the actual items in the array, we only need the id of the food item and the quantity the user want.

The response for this endpoint is a fully filled `Order` object, which you will see below.

#### Fetching all orders

You can fetch all orders that belongs to the currently logged in customer using this endpoint.

`GET "/order/customer/all"`

This returns a list of all orders that belongs to that customer.

```
[
  {
    "id": 0,
    "restaurant": {
      "id": 0,
      "name": "string",
      "profile": "string",
      "available": true,
      "description": "string",
      "address": {
        "id": 0,
        "township": "string",
        "street": "string",
        "additionalDetails": "string"
      }
    },
    "customer": {
      "id": 0,
      "firstname": "string",
      "lastname": "string",
      "username": "string",
      "email": "string",
      "enable": true,
      "profile": "string",
      "available": true,
      "createdAt": "2025-01-14T06:52:05.239Z",
      "updatedAt": "2025-01-14T06:52:05.239Z",
    },
    "items": [
      {
        "id": 0,
        "name": "string",
        "quantity": 0,
        "price": 0,
        "discount": 0,
      }
    ],
    "destination": {
      "id": 0,
      "township": "string",
      "street": "string",
      "additionalDetails": "string"
    },
    "total": 0,
    "completed": true,
    "startedAt": "2025-01-14T06:52:05.239Z",
    "completedAt": "2025-01-14T06:52:05.239Z"
  }
]
```
