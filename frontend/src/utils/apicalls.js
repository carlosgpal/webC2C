import API from "./api";

export { getAllProducts, searchProducts };

function getAllProducts() {
  return API.get("/api/elastic").then((res) => res.data);
}

function searchProducts(query) {
  return API.get(`/api/elastic/search?query=${query}`).then((res) => res.data);
}
