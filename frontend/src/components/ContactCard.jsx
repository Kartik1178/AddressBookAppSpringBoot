/**
 * ContactCard.jsx – Glassmorphism card for a single address book entry.
 * Displays name initial avatar, contact info, and Edit/Delete action buttons.
 */
export default function ContactCard({ contact, onEdit, onDelete }) {
  const initial = contact.name.charAt(0).toUpperCase();

  return (
    <article className="contact-card" id={`contact-card-${contact.id}`}>
      <div className="card-top">
        <div className="avatar" aria-label={`Avatar for ${contact.name}`}>
          {initial}
        </div>
        <div className="card-info">
          <h2 className="card-name">{contact.name}</h2>
          <p className="card-id">ID: {contact.id}</p>
        </div>
      </div>

      <div className="card-phone">
        <svg className="phone-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
          <path strokeLinecap="round" strokeLinejoin="round"
            d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498A1 1 0 0121 15.72V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
        </svg>
        <span>{contact.phoneNumber}</span>
      </div>

      <div className="card-actions">
        <button
          id={`edit-btn-${contact.id}`}
          className="btn btn-edit"
          onClick={() => onEdit(contact)}
          aria-label={`Edit ${contact.name}`}
        >
          <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
            <path strokeLinecap="round" strokeLinejoin="round"
              d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
          </svg>
          Edit
        </button>
        <button
          id={`delete-btn-${contact.id}`}
          className="btn btn-delete"
          onClick={() => onDelete(contact)}
          aria-label={`Delete ${contact.name}`}
        >
          <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
            <path strokeLinecap="round" strokeLinejoin="round"
              d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
          </svg>
          Delete
        </button>
      </div>
    </article>
  );
}
