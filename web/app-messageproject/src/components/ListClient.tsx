import React, { useState } from "react";
import { useEffect } from "react";
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import '../styles/ListClient.css'
import ClientService from '../service/ClientService'
import Loading from "./Loading";
import { Paginator } from "primereact/paginator";

interface Client {
    id?: number;
    name: string;
    cpf: string;
    phoneNumber: number;
    cnpj: string;
    companyName: string;
  }

const ListClient: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const[client , setClient] = useState<Client[]>();
  const [totalRecords, setTotalRecords] = useState(0);
  const [page, setPage] = useState(0);
  const [rows, setRows] = useState(10);

  useEffect(()=> {
    setLoading(true);
    ClientService.findAllClients(page, rows)
    .then((response: any) => {
        console.log(response.data.content.content)
        setClient(response.data.content.content);
        setTotalRecords(response.data.content.totalElements);
      }).catch((error: any) => {
        alert(`Error: ${error.message}`);
      }).finally(()=> {
        setLoading(false);
      })

    }, [page, rows]);

    const onPageChange = (event: any) => {
      setPage(event.page);
      setRows(event.rows);
  };

    return (
       <> 
       { loading ? 
        <Loading/> :
        <>
          <DataTable loading={loading} value={client} paginator={false} rows={rows} className="data-table">
            <Column field="id" header="ID" style={{ width: '10%' }}></Column>
            <Column field="name" header="Name" style={{ width: '15%' }}></Column>
            <Column field="cpf" header="CPF" style={{ width: '15%' }}></Column>
            <Column field="phoneNumber" header="Phone Number" style={{ width: '15%' }}></Column>
            <Column field="cnpj" header="CNPJ" style={{ width: '15%' }}></Column>
            <Column field="companyName" header="Company Name" style={{ width: '15%' }}></Column>
          </DataTable>
          <Paginator first={page * rows} rows={rows} totalRecords={totalRecords} rowsPerPageOptions={[10, 20, 30]} onPageChange={onPageChange}></Paginator>
          </>    
        }</>
    )
}

export default ListClient;