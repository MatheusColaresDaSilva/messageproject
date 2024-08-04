import React, { useEffect, useState } from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import '../styles/FormClient.css';
import ClientService from '../service/ClientService';
import PlanService from '../service/PlanService';
import InputMask from 'react-input-mask';

interface Client {
  name: string;
  cpf: string;
  phoneNumber: string;
  cnpj: string;
  companyName: string;
  planType: string;
}

interface ClientFormValues {
  name: string;
  cpf: string;
  phoneNumber: string;
  cnpj: string;
  companyName: string;
  planType: string;
}

interface Plan {
  id?: string;
  type: string;
}

const validationSchema = Yup.object({
  name: Yup.string().required('Name is required'),
  cpf: Yup.string().required('CPF is required'),
  phoneNumber: Yup.string().required('Phone number is required'),
  cnpj: Yup.string().required('CNPJ is required'),
  companyName: Yup.string().required('Company name is required'),
  plan: Yup.string().required('Plan is required')
});

const ClientForm: React.FC = () => {

  const [plans, setPlans] = useState<Plan[]>([]);
  
  const initialValues: ClientFormValues = {
    name: '',
    cpf: '',
    phoneNumber: '',
    cnpj: '',
    companyName: '',
    planType: ''
  };

  useEffect(() => {
    PlanService.findAllPlans()
      .then((response: any) => {
        setPlans(response.data.content);
      })
      .catch((error: any) => {
        alert(`There was an error fetching the plans!: ${error.message}`);
      });
  }, []);
  
  const createNewClient = (value: ClientFormValues) => {
    return ClientService.createNewClient(value)
      .then((response: any) => {
        alert('New Client added');
      })
      .catch((error: any) => {
        alert(`Error: ${error.message}`);
      });
  };

  const removeMask = (value: string) => {
    return value.replace(/\D/g, '');
  };

  return (
    <Formik
      initialValues={initialValues}
      validationSchema={validationSchema}
      onSubmit={(values, { setSubmitting, resetForm }) => {


        const processedValues = {
          ...values,
          cpf: removeMask(values.cpf),
          phoneNumber: removeMask(values.phoneNumber),
          cnpj: removeMask(values.cnpj)
        };

        createNewClient(processedValues);
        resetForm();
        setSubmitting(false);
      }}
    >
      {({ isSubmitting }) => (
        <Form className="form-container">
          <div className="form-field">
            <label htmlFor="name">Name</label>
            <Field name="name" placeholder="Name" />
            <ErrorMessage name="name" component="div" className="error-message" />
          </div>

          <div className="form-field">
            <label htmlFor="cpf">CPF</label>
            <Field name="cpf">
              {({ field }: { field: any }) => (
                <InputMask {...field} mask="999.999.999-99" />
              )}
            </Field>
            <ErrorMessage name="cpf" component="div" className="error-message" />
          </div>

          <div className="form-field">
            <label htmlFor="phoneNumber">Phone Number</label>
            <Field name="phoneNumber">
              {({ field }: { field: any }) => (
                <InputMask {...field} mask="(99) 99999-9999" />
              )}
            </Field>
            <ErrorMessage name="phoneNumber" component="div" className="error-message" />
          </div>

          <div className="form-field">
            <label htmlFor="cnpj">CNPJ</label>
            <Field name="cnpj">
              {({ field }: { field: any }) => (
                <InputMask {...field} mask="99.999.999/9999-99" />
              )}
            </Field>
            <ErrorMessage name="cnpj" component="div" className="error-message" />
          </div>

          <div className="form-field">
            <label htmlFor="companyName">Company Name</label>
            <Field name="companyName" placeholder="Company Name" />
            <ErrorMessage name="companyName" component="div" className="error-message" />
          </div>

          <div className="form-field">
            <label htmlFor="planType">Plan</label>
            <Field as="select" name="planType">
              <option value="">Select a Plan</option>
                {plans.map((plan) => (
                  <option key={plan.id} value={plan.type}>{plan.type}</option>
                ))}
            </Field>
            <ErrorMessage name="plan" component="div" className="error-message" />
          </div>
          
          <button type="submit" className="add-client-button" disabled={isSubmitting}>
            Create
          </button>
        </Form>
      )}
    </Formik>
  );
};

export default ClientForm;
