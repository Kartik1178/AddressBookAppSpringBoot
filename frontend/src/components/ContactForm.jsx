/**
 * ContactForm.jsx – Modal form for creating or editing an address book entry.
 * Mirrors backend validation rules client-side for instant feedback:
 *   - name: starts with capital, min 3 chars, letters & spaces only
 *   - phoneNumber: 10-digit Indian mobile starting with 6–9
 */
import { useState, useEffect } from 'react';

const EMPTY = { name: '', phoneNumber: '' };

const validate = (fields) => {
  const errs = {};
  if (!fields.name) {
    errs.name = 'Name is required';
  } else if (!/^[A-Z][a-zA-Z ]{2,}$/.test(fields.name)) {
    errs.name = 'Must start with a capital letter and be at least 3 characters';
  }
  if (!fields.phoneNumber) {
    errs.phoneNumber = 'Phone number is required';
  } else if (!/^[6-9]\d{9}$/.test(fields.phoneNumber)) {
    errs.phoneNumber = 'Must be a valid 10-digit Indian mobile number (starts with 6–9)';
  }
  return errs;
};

export default function ContactForm({ mode, initial, onSave, onClose, saving }) {
  const [fields, setFields] = useState(EMPTY);
  const [errors, setErrors] = useState({});
  const [shake, setShake] = useState(false);

  // Populate when editing an existing contact
  useEffect(() => {
    if (initial) {
      setFields({ name: initial.name, phoneNumber: initial.phoneNumber });
    } else {
      setFields(EMPTY);
    }
    setErrors({});
  }, [initial]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFields((prev) => ({ ...prev, [name]: value }));
    // Clear field error on change
    if (errors[name]) setErrors((prev) => ({ ...prev, [name]: '' }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const errs = validate(fields);
    if (Object.keys(errs).length > 0) {
      setErrors(errs);
      setShake(true);
      setTimeout(() => setShake(false), 600);
      return;
    }
    onSave(fields);
  };

  return (
    <div className="modal-backdrop" id="contact-form-backdrop" onClick={onClose}>
      <div
        className={`modal-panel ${shake ? 'shake' : ''}`}
        id="contact-form-modal"
        onClick={(e) => e.stopPropagation()}
        role="dialog"
        aria-modal="true"
        aria-labelledby="form-title"
      >
        <div className="modal-header">
          <h2 id="form-title" className="modal-title">
            {mode === 'create' ? '✦ New Contact' : '✏️ Edit Contact'}
          </h2>
          <button id="close-form-btn" className="modal-close" onClick={onClose} aria-label="Close form">✕</button>
        </div>

        <form onSubmit={handleSubmit} noValidate id="contact-form">
          <div className="form-group">
            <label htmlFor="name-input" className="form-label">Full Name</label>
            <input
              id="name-input"
              name="name"
              type="text"
              className={`form-input ${errors.name ? 'input-error' : ''}`}
              placeholder="e.g. Kartikeya Sharma"
              value={fields.name}
              onChange={handleChange}
              autoComplete="off"
              autoFocus
            />
            {errors.name && <p className="field-error">{errors.name}</p>}
          </div>

          <div className="form-group">
            <label htmlFor="phone-input" className="form-label">Phone Number</label>
            <input
              id="phone-input"
              name="phoneNumber"
              type="tel"
              className={`form-input ${errors.phoneNumber ? 'input-error' : ''}`}
              placeholder="e.g. 9876543210"
              value={fields.phoneNumber}
              onChange={handleChange}
              autoComplete="off"
              maxLength={10}
            />
            {errors.phoneNumber && <p className="field-error">{errors.phoneNumber}</p>}
          </div>

          <div className="form-actions">
            <button id="cancel-form-btn" type="button" className="btn btn-ghost" onClick={onClose} disabled={saving}>
              Cancel
            </button>
            <button id="submit-form-btn" type="submit" className="btn btn-primary" disabled={saving}>
              {saving ? 'Saving…' : mode === 'create' ? 'Add Contact' : 'Save Changes'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
