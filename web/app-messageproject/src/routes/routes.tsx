import { Route, Routes } from 'react-router-dom';
import ClientPage from "../components/ClientPage";
import Home from '../components/Home';
// import ProductPage from '../components/ProductPage';
import ClientForm from '../components/ClientForm';

export const routes = [
    {
      path: '/',
      component: <Home/>,
      children: []
    },
    {
      path: '/client',
      component: <ClientPage/>,
      children: []
    },
    {
      path: '/createClient',
      component: <ClientForm />,
      children: []
    }
  ];


const CustomBrowserRouter = () => (
      <Routes>
        {routes.map((route) => (
          <Route
            key={route.path}
            path={route.path}
            element={route.component}
          />
        ))}
      </Routes>
  );
  
export default CustomBrowserRouter;