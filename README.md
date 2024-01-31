![Marketplace API Banner](.github/banner.jpg)

<h1 align="center" style="font-weight: bold;">
  Marketplace API 🛒
</h1>

<p align="center">
  <a href="#tech">Technologies</a> •
  <a href="#started">Getting Started</a> •
  <a href="#routes">API Endpoints</a> •
  <a href="#colab">Collaborators</a> •
  <a href="#contribute">Contribute</a>
</p>

<p align="center">
  <b>API for managing a marketplace with products, categories and catalogs</b>
</p>

<h2 id="technologies">💻 Technologies</h2>

- Java
- Spring Boot
- Docker
- Postgres
- Redis

<h2 id="started">🚀 Getting started</h2>

<h3>Prerequisites</h3>

- [Git](https://git-scm.com)
- [JDK 17](https://openjdk.org/projects/jdk/17/)
- [Maven](https://maven.apache.org/),
- [Docker and Docker Compose](https://docs.docker.com/)

<h3>Cloning</h3>

```bash
git clone https://github.com/alvesluis0/marketplace-api
```

<h3>Starting</h3>

How to start your project

```bash
cd marketplace-api
docker compose up -d
mvn spring-boot:run
```

<h2 id="routes">📍 API Endpoints</h2>

| route                                   | description                                          
|-----------------------------------------|------------------------------------------------------------
| **CATALOG** | ---
| <kbd>GET /api/catalog/{owner}</kbd>     | retrieves catalog info see [body details](#catalog-detail)
| <kbd>POST /api/catalog</kbd>            | creates a catalog see [body details](#catalog-detail)
| <kbd>DELETE /api/catalog/{owner}</kbd>  | deletes a catalog
| **CATEGORY** | ---
| <kbd>GET /api/category/{id}</kbd>       | retrieves category info see [body details](#category-detail)
| <kbd>POST /api/category</kbd>           | creates a category see [body details](#category-detail)
| <kbd>PUT /api/category/{id}</kbd>       | updates a category see [body details](#category-detail)
| <kbd>DELETE /api/category/{id}</kbd>    | deletes a category
| **PRODUCT** | ---
| <kbd>GET /api/product/{id}</kbd>        | retrieves product info see [body details](#product-detail)
| <kbd>POST /api/product</kbd>            | creates a product see [body details](#product-detail)
| <kbd>PUT /api/product/{id}</kbd>        | updates a product see [body details](#product-detail)
| <kbd>DELETE /api/product/{id}</kbd>     | deletes a product

<h3 id="catalog-detail">CATALOG</h3>

**REQUEST**
```json
{
  "owner": "Luis"
}
```

**RESPONSE**
```json
{
  "owner": "Luis",
  "categories": [
    {
      "id": 1,
      "owner": "Luis",
      "name": "Clothes",
      "description": "Beautiful clothes",
      "products": [
        {
          "id": 1,
          "categoryId": 1,
          "name": "T-shirt",
          "description": "Beautiful t-shirt",
          "price": 19.90
        }
      ]
    }
  ]
}
```

<h3 id="category-detail">CATEGORY</h3>

**REQUEST**
```json
{
  "owner": "Luis", // cannot update
  "name": "Clothes",
  "description": "Beautiful clothes"
}
```

**RESPONSE**
```json
{
  "id": 1,
  "owner": "Luis",
  "name": "Clothes",
  "description": "Beautiful clothes",
  "products": [
    {
      "id": 1,
      "categoryId": 1,
      "name": "T-shirt",
      "description": "Beautiful t-shirt",
      "price": 19.90
    }
  ]
}
```

<h3 id="product-detail">PRODUCT</h3>

**REQUEST**
```json
{
  "categoryId": 1, // cannot update
  "name": "T-shirt",
  "description": "Beautiful t-shirt",
  "price": 19.90
}
```

**RESPONSE**
```json
{
  "id": 1,
  "categoryId": 1,
  "name": "T-shirt",
  "description": "Beautiful t-shirt",
  "price": 19.90
}
```

<h2 id="colab">🤝 Collaborators</h2>

Special thank you for all people that contributed for this project.

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/alvesluis0">
        <img
          src="https://avatars.githubusercontent.com/u/104936483?v=4"
          width="100px;"
          alt="Alves Luis Profile Picture"
        /><br>
        <sub>
          <b>Alves Luis</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

<h2 id="contribute">📫 Contribute</h2>

1. `git clone https://github.com/alvesluis0/marketplace-api`
2. `git checkout -b feature/NAME`
3. Follow commit patterns
4. Open a Pull Request explaining the problem solved or feature made, if exists, append screenshot of visual modifications and wait for the review!

<h3>Documentations that might help</h3>

[📝 How to create a Pull Request](https://www.atlassian.com/br/git/tutorials/making-a-pull-request)

[💾 Commit pattern](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)