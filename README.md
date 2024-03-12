# CartAPI
### Spring Boot Maven Project

This is a Spring Boot Maven project. It includes a simple web application that exposes a REST API. This API is built using the FakeStoreAPI.

## Prerequisites

To build and run this project, you will need the following:

* Java JDK 8 or higher
* Maven 3
* Git

## Running the application locally
To run the application, clone the repository.
```
git clone "https://github.com/arthurW1935/CartAPI/"
cd CartAPI
```

Locallly, one can execute the `main` method of the `CartApiApplication.java` file which is present in `src/main/java/com/arthur/cartapi/` folder.

## Endpoints

`GET` 
- `/carts` : get all the available carts
  ![image](https://github.com/arthurW1935/CartAPI/assets/69480979/44791962-9d49-41fc-b9cb-e54703badaf5)

- `/carts/{id}` : get the cart with the specific `id` (`id` is an integer)
  ![image](https://github.com/arthurW1935/CartAPI/assets/69480979/c6417ecd-ace2-47dc-bd01-62abc43f7629)

- `/carts?startdate={startDate}&enddatde={endDate}` : get all the carts within the range of `startDate` and `endDate` (`startDate` and `endDate` is a string of date)
  ![image](https://github.com/arthurW1935/CartAPI/assets/69480979/b7174ae9-b8e1-4075-854f-732cbc7d0fac)

- `/carts/user/{id}` : get the cart of the specific user (`id` is an integer)
  ![image](https://github.com/arthurW1935/CartAPI/assets/69480979/b06b29f3-f3b3-492f-85e2-3acac5d05710)


`POST`
- `/carts` : add a new cart.
  ![image](https://github.com/arthurW1935/CartAPI/assets/69480979/2de8974d-8437-4f0f-92cd-1ac0e061bfe2)


`PUT`
- `/carts/{id}` : update a specific cart (`id` is an integer)
  ![image](https://github.com/arthurW1935/CartAPI/assets/69480979/64a50f72-849c-46df-b57b-c88abdcc14e5)


`DELETE`
- `/carts/id` : delete the specific cart (`id` is an integer`)
  ![image](https://github.com/arthurW1935/CartAPI/assets/69480979/1010ccd2-60d8-4883-b08c-847d01a08b96)
