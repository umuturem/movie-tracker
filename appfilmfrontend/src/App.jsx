import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import AllFilms from './pages/AllFilms';
import Watched from './pages/Watched';
import WatchList from './pages/WatchList';
import MainLayout from './layout/MainLayout';
import RequireAuth from './components/RequireAuth';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const App = () => {
  return (
    <>
      <ToastContainer   
        position="bottom-right"
        autoClose={3000}
        hideProgressBar={true}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="light"
        limit={3}
        toastClassName="text-sm"
        bodyClassName="text-gray-700"
      />
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<LoginPage />} />

          <Route path="/" element={
            <RequireAuth>
              <MainLayout />
            </RequireAuth>
          }>
            <Route path="all-films" element={<AllFilms />} />
            <Route path="watched" element={<Watched />} />
            <Route path="watchlist" element={<WatchList />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default App;
