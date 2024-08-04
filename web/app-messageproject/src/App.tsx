import './App.css';
import CustomBrowserRouter from './routes/routes';
import { BrowserRouter } from 'react-router-dom';
import Sidebar from './components/Sidebar';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Sidebar />
        <div className="main-content">
          <CustomBrowserRouter />
        </div>
      </div>
    </BrowserRouter>
  );
}

export default App;
