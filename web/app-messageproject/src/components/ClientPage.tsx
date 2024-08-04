import React from 'react';
import ListClient from './ListClient';
import FabButton from './FabButton';
import { FaAddressCard } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';

const ClientPage: React.FC = () => {

  const navigate = useNavigate();
  
  return (
    <>
      <ListClient />
      <FabButton options={[
            {label: 'Create', value: <FaAddressCard/>,  action: ()=> navigate('/createClient')},
          ]}/>
    </>
  );
};

export default ClientPage;