import React, { useEffect, useState } from 'react';
import axios from 'axios';
import FilmCard from '../components/FilmCard';

const Watched = () => {
  const [watchedFilms, setWatchedFilms] = useState([]);
  const [loading, setLoading] = useState(true);

  const user = JSON.parse(localStorage.getItem('user'));
  const userId = user ? user.id : null;

  useEffect(() => {
    const fetchWatchedFilms = async () => {
      try {
        setLoading(true);
        const response = await axios.get(`http://localhost:8080/api/watched/${userId}`);
        setWatchedFilms(response.data);
      } catch (error) {
        console.error('Watched filmler alınamadı:', error);
      } finally {
        setLoading(false);
      }
    };

    if (userId) {
      fetchWatchedFilms();
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
        <h1 className="text-3xl font-bold text-gray-900 mb-2">Watched Movies</h1>
        <p className="text-gray-600">Movies you have watched</p>
      </div>

      {watchedFilms.length === 0 ? (
        <div className="text-center py-16">
          <div className="w-16 h-16 bg-gray-100 rounded-full flex items-center justify-center mx-auto mb-4">
            <svg className="w-8 h-8 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M15 10l4.553-2.276A1 1 0 0121 8.618v6.764a1 1 0 01-1.447.894L15 14M5 18h8a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v8a2 2 0 002 2z" />
            </svg>
          </div>
          <h3 className="text-xl font-semibold text-gray-900 mb-2">No movies watched yet</h3>
          <p className="text-gray-600 mb-6 max-w-md mx-auto">
            Start building your collection by watching your first movie.
          </p>
          <a 
            href="/all-films" 
            className="inline-block bg-gray-900 text-white px-6 py-3 rounded-lg hover:bg-gray-800 transition-colors font-medium"
          >
            Browse Movies
          </a>
        </div>
      ) : (
        <>
          <div className="mb-6">
            <span className="text-sm text-gray-600">
              {watchedFilms.length} movies watched
            </span>
          </div>
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
            {watchedFilms.map((film) => (
              <FilmCard
                key={film.filmId}
                film={film}
                userId={userId}
                isWatchedPage={true}
              />
            ))}
          </div>
        </>
      )}
    </div>
  );
};

export default Watched;

