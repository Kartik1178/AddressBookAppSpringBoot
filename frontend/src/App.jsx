/**
 * App.jsx – Root component for the AddressBook React frontend.
 * Manages global state: contacts list, modal visibility, CRUD operations,
 * and notification toasts. All API calls delegate to addressBookApi.js.
 */
import { useState, useEffect, useCallback } from 'react';
import { getAll, create, update, remove } from './api/addressBookApi';
import Header from './components/Header';
import ContactList from './components/ContactList';
import ContactForm from './components/ContactForm';
import ConfirmModal from './components/ConfirmModal';

export default function App() {
  // ── data state ──────────────────────────────
  const [contacts, setContacts] = useState([]);
  const [loading, setLoading]   = useState(true);
  const [error, setError]       = useState(null);

  // ── modal state ──────────────────────────────
  const [formMode, setFormMode]       = useState(null);   // 'create' | 'edit' | null
  const [editTarget, setEditTarget]   = useState(null);   // contact being edited
  const [deleteTarget, setDeleteTarget] = useState(null); // contact pending deletion

  // ── pending-operation flags ──────────────────
  const [saving, setSaving]   = useState(false);
  const [deleting, setDeleting] = useState(false);

  // ── toast notification ──────────────────────
  const [toast, setToast] = useState(null); // { type: 'success'|'error', msg: string }

  const showToast = (type, msg) => {
    setToast({ type, msg });
    setTimeout(() => setToast(null), 3500);
  };

  // ── fetch all contacts ───────────────────────
  const fetchContacts = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const res = await getAll();
      setContacts(res.data.data || []);
    } catch {
      setError('Failed to fetch contacts. Is the backend running?');
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => { fetchContacts(); }, [fetchContacts]);

  // ── create / edit save ────────────────────────
  const handleSave = async (fields) => {
    setSaving(true);
    try {
      if (formMode === 'create') {
        await create(fields);
        showToast('success', 'Contact added successfully!');
      } else {
        await update(editTarget.id, fields);
        showToast('success', 'Contact updated successfully!');
      }
      setFormMode(null);
      setEditTarget(null);
      await fetchContacts();
    } catch (err) {
      const msg = err.response?.data?.message || 'Something went wrong. Please try again.';
      showToast('error', msg);
    } finally {
      setSaving(false);
    }
  };

  // ── delete ────────────────────────────────────
  const handleDeleteConfirm = async () => {
    setDeleting(true);
    try {
      await remove(deleteTarget.id);
      showToast('success', `"${deleteTarget.name}" deleted.`);
      setDeleteTarget(null);
      await fetchContacts();
    } catch {
      showToast('error', 'Failed to delete contact.');
    } finally {
      setDeleting(false);
    }
  };

  // ── open edit form ───────────────────────────
  const openEdit = (contact) => {
    setEditTarget(contact);
    setFormMode('edit');
  };

  return (
    <div className="app-root">
      <Header />

      <main className="main-content" id="main-content">
        {/* ── toolbar ── */}
        <div className="toolbar">
          <p className="contact-count">
            {loading ? '–' : contacts.length} Contact{contacts.length !== 1 ? 's' : ''}
          </p>
          <button
            id="add-contact-btn"
            className="btn btn-primary btn-lg"
            onClick={() => setFormMode('create')}
          >
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5">
              <line x1="12" y1="5" x2="12" y2="19" />
              <line x1="5" y1="12" x2="19" y2="12" />
            </svg>
            Add Contact
          </button>
        </div>

        <ContactList
          contacts={contacts}
          loading={loading}
          error={error}
          onEdit={openEdit}
          onDelete={(c) => setDeleteTarget(c)}
        />
      </main>

      {/* ── create / edit modal ── */}
      {formMode && (
        <ContactForm
          mode={formMode}
          initial={editTarget}
          onSave={handleSave}
          onClose={() => { setFormMode(null); setEditTarget(null); }}
          saving={saving}
        />
      )}

      {/* ── delete confirmation ── */}
      {deleteTarget && (
        <ConfirmModal
          contact={deleteTarget}
          onConfirm={handleDeleteConfirm}
          onCancel={() => setDeleteTarget(null)}
          deleting={deleting}
        />
      )}

      {/* ── toast notifications ── */}
      {toast && (
        <div
          id="toast-notification"
          className={`toast toast-${toast.type}`}
          role="status"
          aria-live="polite"
        >
          {toast.type === 'success'
            ? '✓ '
            : '✕ '}
          {toast.msg}
        </div>
      )}
    </div>
  );
}
