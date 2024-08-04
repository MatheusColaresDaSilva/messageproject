import axios, { AxiosResponse } from 'axios';

const BASE_URL = 'http://localhost:8080/api/v1/item';

// const apiClient = axios.create({
//   baseURL: BASE_URL,
//   headers: {
//     'Content-Type': 'application/json',
//     // You can add other headers like authorization token here
//   },
// });

// Define common API methods
// const _get = (url, config = {}) => {
//   return apiClient.get(url, config);
// };

// const _delete = (url, config = {}) => {
//   return apiClient.delete(url, config);
// };

// const _put = (url, data = {}, config = {}) => {
//   return apiClient.put(url, data, config);
// };

// const _post = (url, data = {}, config = {}) => {
//   return apiClient.post(url, data, config);
// };

type Product = {
  id?: number;
  description: string;
};

async function createNewProduct(product: Product): Promise<Product | null> {
    return await axios.post(BASE_URL, product);
}

async function findAllProducts(page: number, size: number ): Promise<AxiosResponse> {
  return await axios.get(BASE_URL, {
    params: {
      page: page,
      size: size 
    }
  });
}


const exportedObject = {
  createNewProduct,
  findAllProducts
};

export default exportedObject;