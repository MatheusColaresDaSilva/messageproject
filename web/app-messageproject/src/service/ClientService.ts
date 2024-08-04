import axios, { AxiosResponse } from 'axios';

const BASE_URL = 'http://localhost:8080/api/v1/client';

type Client = {
  id?: number;
  name: string;
  cpf: string;
  phoneNumber: string;
  cnpj: string;
  companyName: string;
};

async function createNewClient(client: Client): Promise<Client | null> {
  return await axios.post(BASE_URL, client);
}

async function findAllClients(page: number, size: number ): Promise<AxiosResponse> {
  return await axios.get(BASE_URL, {
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