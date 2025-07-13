import React, { useEffect, useState } from 'react';
import axios from 'axios';
import FilmCard from '../components/FilmCard';

const WatchList = () => {
  const [watchListFilms, setWatchListFilms] = useState([]);
  const [loading, setLoading] = useState(true);

  const user = JSON.parse(localStorage.getItem('user'));
  const userId = user ? user.id : null;

  useEffect(() => {
    const fetchWatchListFilms = async () => {
      try {
        setLoading(true);
        const response = await axios.get(`http://localhost:8080/api/watchlist/${userId}`);
        setWatchListFilms(response.data);
      } catch (error) {
        console.error('WatchList filmler alınamadı:', error);
      } finally {
        setLoading(false);
      }
    };

    if (userId) {
      fetchWatchListFilms();
    }
  }, [userId]);

  if (loading) {
    return (
      <div className="flex justify-center items-center min-h-[400px]">
        <div className="animate-spin rounded-full h-12 w-12 border-2 border-gray-300 border-t-gray-900"></div>
      </div>
    );
  }

      return (
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900 mb-2">My Watchlist</h1>
        <p className="text-gray-600">Movies you want to watch</p>
      </div>

      {watchListFilms.length === 0 ? (
        <div className="text-center py-16">
          <div className="w-16 h-16 bg-gray-100 rounded-full flex items-center justify-center mx-auto mb-4">
            <svg className="w-8 h-8 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 10h16M4 14h16M4 18h16" />
            </svg>
          </div>
          <h3 className="text-xl font-semibold text-gray-900 mb-2">Your watchlist is empty</h3>
          <p className="text-gray-600 mb-6 max-w-md mx-auto">
            You haven't added any movies to watch yet. Browse movies and add the ones that interest you to your list.
          </p>
          <a 
            href="/all-films" 
            className="inline-block bg-gray-900 text-white px-6 py-3 rounded-lg hover:bg-gray-800 transition-colors font-medium"
          >
            Discover Movies
          </a>
        </div>
      ) : (
        <>
          <div className="mb-6">
            <span className="text-sm text-gray-600">
              {watchListFilms.length} movies in your list
            </span>
          </div>
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
            {watchListFilms.map((film) => (
              <FilmCard
                key={film.filmId}
                film={film}
                userId={userId}
                isWatchListPage={true}
              />
            ))}
          </div>
        </>
      )}
    </div>
  );
};

export default WatchList;
