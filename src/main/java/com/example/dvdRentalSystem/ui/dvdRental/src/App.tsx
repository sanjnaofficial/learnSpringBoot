import { useState } from 'react'
import './App.css'
import Home from './components/Home'
import DvdView from './components/DvdView'
import ActorView from './components/ActorView'

type Page = 'home' | 'dvd' | 'actor'

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false)
  const [currentPage, setCurrentPage] = useState<Page>('home')

  const handleLogin = () => setIsLoggedIn(true)
  const handleLogout = () => {
    setIsLoggedIn(false)
    setCurrentPage('home')
  }

  const renderPage = () => {
    if (!isLoggedIn) return <Home onLogin={handleLogin} />
    switch (currentPage) {
      case 'dvd':
        return <DvdView />
      case 'actor':
        return <ActorView />
      default:
        return <Home onLogin={handleLogin} />
    }
  }

  return (
    <div className="app-container">
      {isLoggedIn && (
        <nav className="menu-bar">
          <button onClick={() => setCurrentPage('dvd')}>DVDs</button>
          <button onClick={() => setCurrentPage('actor')}>Actors</button>
          <button onClick={handleLogout} className="logout-btn">Logout</button>
        </nav>
      )}
      {renderPage()}
    </div>
  )
}

export default App
