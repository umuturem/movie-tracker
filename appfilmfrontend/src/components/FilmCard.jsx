import React from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';

const FilmCard = ({ film, userId, isWatchedPage = false, isWatchListPage = false }) => {
  
  const handleAddToWatchlist = async () => {
    try {
      await axios.post('http://localhost:8080/api/watchlist', {
        userId: userId,
        filmId: film.id,
      });
      toast.success('Added to Watchlist');
    } catch (error) {
      console.error('Watchlist eklenirken hata:', error);
      toast.error('Failed to add. Please try again.');
    }
  };

  const handleAddToWatched = async () => {
    try {
      await axios.post('http://localhost:8080/api/watched', {
        userId: userId,
        filmId: film.id,
      });
      toast.success('Added to Watched');
    } catch (error) {
      console.error('Eklenirken hata:', error);
      toast.error('Failed to add. Please try again.');
    }
  };

  const handleRemoveFromWatched = async () => {
    try {
      const filmId = film.filmId ?? film.id;
      await axios.delete(`http://localhost:8080/api/watched/${userId}/${filmId}`);
      toast.info('Removed from Watched');
      setTimeout(() => {
        window.location.reload();
      }, 2000);
      
    } catch (error) {
      console.error('Kaldırılırken hata:', error);
      toast.error('Failed to remove. Please try again.');
    }
  };

  const handleRemoveFromWatchList = async () => {
    try {
      const filmId = film.filmId ?? film.id;
      await axios.delete(`http://localhost:8080/api/watchlist/${userId}/${filmId}`);
      toast.info('Removed from Watchlist');
      setTimeout(() => {
        window.location.reload();
      }, 2000);
    } catch (error) {
      console.error('Watchlist\'ten kaldırılırken hata:', error);
      toast.error('Failed to remove. Please try again.');
    }
  };

  return (
    <div className="bg-white rounded-xl shadow-sm hover:shadow-md transition-all duration-300 border border-gray-100 overflow-hidden group">
      <div className="relative w-full h-[280px] overflow-hidden">
        <img
          src={`/images/${film.imageName}`}
          alt={film.title}
          className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500"
        />
        <div className="absolute inset-0 bg-gradient-to-t from-black/30 via-transparent to-transparent opacity-0 group-hover:opacity-100 transition-opacity duration-300"></div>
      </div>

      <div className="p-5">
        <h3 className="text-lg font-semibold text-gray-900 mb-2 line-clamp-2 group-hover:text-gray-700 transition-colors">
          {film.title}
        </h3>
        <p className="text-gray-600 text-sm leading-relaxed mb-4 line-clamp-3">
          {film.description}
        </p>

        <div className="space-y-2">
          {isWatchedPage ? (
            <button
              onClick={handleRemoveFromWatched}
              className="w-full bg-gray-900 text-white py-2.5 px-4 rounded-lg hover:bg-gray-800 transition-all duration-200 text-sm font-medium border border-gray-900 hover:border-gray-800"
            >
              Remove
            </button>
          ) : isWatchListPage ? (
            <div className="space-y-2">
              <button
                onClick={handleAddToWatched}
                className="w-full bg-gray-900 text-white py-2.5 px-4 rounded-lg hover:bg-gray-800 transition-all duration-200 text-sm font-medium border border-gray-900 hover:border-gray-800"
              >
                Mark as Watched
              </button>
              <button
                onClick={handleRemoveFromWatchList}
                className="w-full bg-white text-gray-700 py-2.5 px-4 rounded-lg hover:bg-gray-50 transition-all duration-200 text-sm font-medium border border-gray-300 hover:border-gray-400"
              >
                Remove from List
              </button>
            </div>
          ) : (
            <div className="space-y-2">
              <button
                onClick={handleAddToWatched}
                className="w-full bg-gray-900 text-white py-2.5 px-4 rounded-lg hover:bg-gray-800 transition-all duration-200 text-sm font-medium border border-gray-900 hover:border-gray-800"
              >
                Mark as Watched
              </button>
              <button
                onClick={handleAddToWatchlist}
                className="w-full bg-white text-gray-700 py-2.5 px-4 rounded-lg hover:bg-gray-50 transition-all duration-200 text-sm font-medium border border-gray-300 hover:border-gray-400"
              >
                Add to Watchlist
              </button>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default FilmCard;
