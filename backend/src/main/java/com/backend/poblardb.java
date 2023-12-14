// package com.backend;

// import java.io.IOException;
// import java.net.URI;
// import java.net.http.HttpClient;
// import java.net.http.HttpRequest;
// import java.net.http.HttpResponse;
// import java.time.LocalDateTime;
// import java.time.ZoneId;
// import java.time.ZonedDateTime;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
// import java.util.UUID;

// import org.json.JSONArray;
// import org.json.JSONObject;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.ApplicationContext;

// import com.backend.dto.ImageDTO;
// import com.backend.dto.ProductDTO;
// import com.backend.dto.TagDTO;
// import com.backend.dto.UserDTO;
// import com.backend.model.ProductElastic;
// import com.backend.model.TagElastic;
// import com.backend.service.ProductElasticService;
// import com.backend.service.ProductService;
// import com.backend.service.UserService;

// @SpringBootApplication
// public class poblardb {
// @Autowired
// private ProductElasticService productElasticService;
// @Autowired
// private ProductService productService;
// @Autowired
// private UserService userService;

// public static void main(String[] args) throws IOException,
// InterruptedException {
// ApplicationContext context = SpringApplication.run(poblardb.class, args);
// poblardb app = context.getBean(poblardb.class);
// app.run();
// }

// public void run() throws IOException, InterruptedException {

// UserDTO user = createUserFromApi();
// List<ProductDTO> productDTOs = new ArrayList<>();

// HttpRequest request = HttpRequest.newBuilder()
// .uri(URI.create(
// "https://real-time-product-search.p.rapidapi.com/search?q=Nike%20Shoes&country=us&language=en&limit=30"))
// .header("X-RapidAPI-Key",
// "f778873164mshbd14da0bfba06d8p1eab3djsn888e67403762")
// .header("X-RapidAPI-Host", "real-time-product-search.p.rapidapi.com")
// .method("GET", HttpRequest.BodyPublishers.noBody())
// .build();
// HttpResponse<String> response = HttpClient.newHttpClient().send(request,
// HttpResponse.BodyHandlers.ofString());

// JSONObject responseJson = new JSONObject(response.body());
// JSONArray productsArray = responseJson.getJSONArray("data");
// LocalDateTime now = LocalDateTime.now();

// for (int i = 0; i < productsArray.length(); i++) {
// JSONObject productJson = productsArray.getJSONObject(i);
// ProductDTO product = createProductFromJson(productJson, now);
// ProductElastic productElastic = createProductElasticFromJson(productJson,
// convertToLocalDateViaInstant(now));
// productDTOs.add(product);
// // Guardar en la base de datos usando los servicios
// productService.createProduct(product);
// productElasticService.createProductElastic(new ProductElastic(
// productElastic.getIdproduct(),
// productElastic.getName(),
// productElastic.getDescription(),
// productElastic.getPrice(),
// productElastic.getRating(),
// productElastic.getDate(),
// productElastic.getPlace(),
// productElastic.getThumbnailimg(),
// productElastic.getTags(),
// user.getIduser()));
// }

// user.setProducts(productDTOs);
// userService.createUser(user);
// System.out.println("TERMINADO");
// }

// private static ProductDTO createProductFromJson(JSONObject productJson,
// LocalDateTime now) {
// String id = productJson.getString("product_id");
// String name = productJson.getString("product_title");
// String truncatedName;
// if (name.length() > 1000) {
// truncatedName = name.substring(0, 1000);
// } else {
// truncatedName = name;
// }
// String description = productJson.getString("product_description");
// String truncateddescription;
// if (description.length() > 1000) {
// truncateddescription = description.substring(0, 1000);
// } else {
// truncateddescription = description;
// }
// String priceStr =
// productJson.getJSONObject("offer").getString("price").replaceAll("[^\\d.]",
// "");
// double price = Double.parseDouble(priceStr);
// double rating = productJson.optDouble("product_rating", 0);
// String place = "Barcelona"; // Ejemplo de lugar

// // Crear lista de ImageDTO
// List<ImageDTO> imageDTOs = new ArrayList<>();
// JSONArray photos = productJson.getJSONArray("product_photos");
// for (int i = 0; i < photos.length(); i++) {
// String imageId = UUID.randomUUID().toString(); // Generar u
// imageDTOs.add(new ImageDTO(imageId, photos.getString(i)));
// }

// // Crear lista de TagDTO
// List<TagDTO> tagDTOs = new ArrayList<>();
// JSONObject attributes = productJson.getJSONObject("product_attributes");
// for (String key : attributes.keySet()) {
// String value = attributes.getString(key);
// // Split the value by commas to handle multiple tags in one line
// String[] parts = value.split(",");
// for (String part : parts) {
// // Trim each part to remove leading and trailing spaces
// part = part.trim();
// // Generate a unique ID for each tag
// String tagId = UUID.randomUUID().toString();
// // Add the trimmed part as a new TagDTO
// tagDTOs.add(new TagDTO(tagId, part));
// }
// }

// return new ProductDTO(id, truncatedName, truncateddescription, price, rating,
// now,
// place,
// imageDTOs, tagDTOs);
// }

// private static ProductElastic createProductElasticFromJson(JSONObject
// productJson, Date now) {
// String id = productJson.getString("product_id");
// String name = productJson.getString("product_title");
// String truncatedName = name.length() > 1000 ? name.substring(0, 1000) : name;
// String description = productJson.getString("product_description");
// String truncatedDescription = description.length() > 1000 ?
// description.substring(0, 1000) : description;
// String priceStr =
// productJson.getJSONObject("offer").getString("price").replaceAll("[^\\d.]",
// "");
// double price = Double.parseDouble(priceStr);
// double rating = productJson.optDouble("product_rating", 0);
// JSONArray photos = productJson.getJSONArray("product_photos");

// ProductElastic productElastic = new ProductElastic(
// id, truncatedName, truncatedDescription, price, rating, now, "Barcelona",
// photos.getString(0),
// new ArrayList<>(), "Juanito");

// JSONObject attributes = productJson.getJSONObject("product_attributes");
// for (String key : attributes.keySet()) {
// String value = attributes.getString(key);
// String[] parts = value.split(",");
// for (String part : parts) {
// part = part.trim(); // Trim the part to remove leading and trailing spaces
// productElastic.getTags().add(new TagElastic(key, part));
// }
// }

// return productElastic;
// }

// private static Date convertToLocalDateViaInstant(LocalDateTime dateToConvert)
// {
// return
// java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
// }

// private UserDTO createUserFromApi() throws IOException, InterruptedException
// {
// // La petición a la API de usuario
// HttpRequest requestUser = HttpRequest.newBuilder()
// .uri(URI.create("https://random-user-api.p.rapidapi.com/api"))
// .header("X-RapidAPI-Key",
// "f778873164mshbd14da0bfba06d8p1eab3djsn888e67403762")
// .header("X-RapidAPI-Host", "random-user-api.p.rapidapi.com")
// .method("GET", HttpRequest.BodyPublishers.noBody())
// .build();
// HttpResponse<String> responseUser =
// HttpClient.newHttpClient().send(requestUser,
// HttpResponse.BodyHandlers.ofString());

// // Convertir la respuesta en JSON
// JSONObject userJson = new
// JSONObject(responseUser.body()).getJSONArray("results").getJSONObject(0);

// // Crear el UserDTO
// String userId = UUID.randomUUID().toString();
// String name = userJson.getJSONObject("name").getString("first") + " "
// + userJson.getJSONObject("name").getString("last");
// String profilePic = userJson.getJSONObject("picture").getString("thumbnail");
// String email = userJson.getString("email");
// String pass = userJson.getJSONObject("login").getString("md5");
// String dobString = userJson.getJSONObject("dob").getString("date");
// ZonedDateTime dobZoned = ZonedDateTime.parse(dobString);
// LocalDateTime lastTime = dobZoned.toLocalDateTime();

// return new UserDTO(userId, name, profilePic, email, pass, lastTime, true,
// "https://example.com/verify",
// new ArrayList<>());
// }
// }
