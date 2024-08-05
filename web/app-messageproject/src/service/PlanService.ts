import axios, { AxiosResponse } from 'axios';
import Config from './config'

async function findAllPlans(): Promise<AxiosResponse> {
  return await axios.get(Config.baseUrl + "/plan");
}


const PlanService = {
  findAllPlans
};

export default PlanService;