import axios, { AxiosResponse } from 'axios';
import Config from './config'

type Client = {
  id?: number;
  name: string;
  cpf: string;
  phoneNumber: string;
  cnpj: string;
  companyName: string;
};

async function createNewClient(client: Client): Promise<Client | null> {
  return await axios.post(Config.baseUrl + "/client", client);
}

async function findAllClients(page: number, size: number ): Promise<AxiosResponse> {
  return await axios.get(Config.baseUrl + "/client", {
    params: {
      page: page,
      size: size 
    }
  });
}


const ClientService = {
  createNewClient,
  findAllClients
};

export default ClientService;