import axios, { AxiosResponse } from 'axios';

const BASE_URL = 'http://localhost:8080/api/v1/plan';

async function findAllPlans(): Promise<AxiosResponse> {
  return await axios.get(BASE_URL);
}


const PlanService = {
  findAllPlans
};

export default PlanService;