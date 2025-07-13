import React from 'react';
import Navbar from '../components/Navbar';
import { Outlet } from 'react-router-dom';

const MainLayout = () => {
  return (
    <div className="min-h-screen bg-gray-50 flex flex-col">
      <Navbar />
      <main className="flex-1 py-8">
        <Outlet />
      </main>
      
      <footer className="bg-white border-t border-gray-200 py-12 mt-auto">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center">
            <div className="flex items-center justify-center space-x-2 mb-4">
              <div className="w-8 h-8 bg-gray-900 rounded-lg flex items-center justify-center">
                <span className="text-white text-sm font-bold">M</span>
              </div>
              <span className="text-xl font-semibold text-gray-900">
                Movie Tracker
              </span>
            </div>
            <p className="text-gray-600 text-sm">
              © 2024 Movie Tracker. The modern way to discover movies.
            </p>
          </div>
        </div>
      </footer>
    </div>
  );
};

export default MainLayout;
