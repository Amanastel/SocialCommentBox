# Social Comment Box

Social Comment Box is a Spring Boot application that allows users to create, manage, and interact with posts and comments. This project demonstrates a basic social media platform with user registration, posting, and commenting functionalities.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Spring MVC
- PostgreSQL
- Lombok
- Maven

## Features

- User Registration
- Creating Posts
- Adding Comments to Posts
- Retrieving Posts and Comments
- Deleting Posts, Comments, and Users

## Getting Started

To get started with the Social Comment Box project, follow these steps:

1. Clone the repository: `git clone https://github.com/Amanastel/SocialCommentBox.git`
2. Make sure you have Java 17 and Maven installed.
3. Set up a PostgreSQL database and update the application.properties file with your database configuration.
4. Build and run the project: `mvn spring-boot:run`

## Usage

1. Register a new user using the API endpoint.
2. Create posts using the API.
3. Add comments to posts using the API.
4. Retrieve posts and comments.
5. Delete posts, comments, or users when needed.

## API Endpoints

- User-related endpoints:
  - Register a user: `POST /users`
  - Get a user by ID: `GET /users/{id}`
  - Get all users: `GET /users/all`
  - Delete a user by ID: `DELETE /users/{id}`
  - Get a user by username: `GET /users/name/{name}`

- Post-related endpoints:
  - Create a post: `POST /posts/{username}`
  - Get a post by ID: `GET /posts/{id}`
  - Delete a post by ID: `DELETE /posts/{id}`
  - Get all posts by user: `GET /posts/user/{username}`

- Comment-related endpoints:
  - Create a comment: `POST /comments/{postId}/{username}`
  - Get a comment by ID: `GET /comments/{id}`
  - Delete a comment by ID: `DELETE /comments/{id}`
  - Get all comments by user: `GET /comments/user/{username}`
  - Get all comments for a post: `GET /comments/post/{postId}`

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and test them.
4. Submit a pull request to the main repository.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
