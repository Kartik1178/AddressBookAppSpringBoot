/**
 * Header.jsx – App-level header with branding, gradient title, and subtitle.
 */
export default function Header() {
  return (
    <header className="app-header">
      <div className="header-inner">
        <div className="logo-area">
          <div className="logo-icon">
            <svg viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg">
              <rect width="40" height="40" rx="10" fill="url(#grad)" />
              <path d="M20 10C16.13 10 13 13.13 13 17c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5A2.5 2.5 0 1120 14a2.5 2.5 0 010 5.5z" fill="white" />
              <defs>
                <linearGradient id="grad" x1="0" y1="0" x2="40" y2="40">
                  <stop offset="0%" stopColor="#7c3aed" />
                  <stop offset="100%" stopColor="#4f46e5" />
                </linearGradient>
              </defs>
            </svg>
          </div>
          <div className="logo-text">
            <h1 className="app-title">Address<span className="accent">Book</span></h1>
            <p className="app-subtitle">Manage your contacts with elegance</p>
          </div>
        </div>
      </div>
    </header>
  );
}
