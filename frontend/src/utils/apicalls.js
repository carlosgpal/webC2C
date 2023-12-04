import API from "./api";

export {
  getAllProducts,
};

function getAllProducts() {
  return API.get("/api/elastic").then((res) => res.data);
}
