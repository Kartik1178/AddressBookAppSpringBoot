/**
 * ContactList.jsx – Renders the responsive grid of ContactCards.
 * Handles loading spinner, empty-state illustration, and error banner.
 */
import ContactCard from './ContactCard';

export default function ContactList({ contacts, loading, error, onEdit, onDelete }) {
  if (loading) {
    return (
      <div className="state-container" id="loading-state">
        <div className="spinner" aria-label="Loading contacts" />
        <p className="state-text">Loading contacts…</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="state-container error-state" id="error-state">
        <svg className="state-icon error-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
          <circle cx="12" cy="12" r="10" />
          <line x1="12" y1="8" x2="12" y2="12" />
          <line x1="12" y1="16" x2="12.01" y2="16" />
        </svg>
        <p className="state-text">{error}</p>
      </div>
    );
  }

  if (contacts.length === 0) {
    return (
      <div className="state-container" id="empty-state">
        <svg className="state-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5">
          <path strokeLinecap="round" strokeLinejoin="round"
            d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0z" />
        </svg>
        <p className="state-text">No contacts yet. Add your first one!</p>
      </div>
    );
  }

  return (
    <section className="contact-grid" id="contact-list" aria-label="Contact list">
      {contacts.map((contact) => (
        <ContactCard
          key={contact.id}
          contact={contact}
          onEdit={onEdit}
          onDelete={onDelete}
        />
      ))}
    </section>
  );
}
