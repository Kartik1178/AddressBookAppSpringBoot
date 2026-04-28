/**
 * ConfirmModal.jsx – Delete-confirmation dialog.
 * Prevents accidental deletions with a two-step confirm flow.
 */
export default function ConfirmModal({ contact, onConfirm, onCancel, deleting }) {
  return (
    <div className="modal-backdrop" id="confirm-modal-backdrop" onClick={onCancel}>
      <div
        className="modal-panel confirm-panel"
        id="confirm-modal"
        onClick={(e) => e.stopPropagation()}
        role="alertdialog"
        aria-modal="true"
        aria-labelledby="confirm-title"
      >
        <div className="confirm-icon-wrap">
          <svg className="confirm-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
            <path strokeLinecap="round" strokeLinejoin="round"
              d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
          </svg>
        </div>

        <h2 id="confirm-title" className="confirm-title">Delete Contact?</h2>
        <p className="confirm-body">
          You are about to permanently delete <strong>{contact?.name}</strong>.<br />
          This action cannot be undone.
        </p>

        <div className="form-actions">
          <button id="cancel-delete-btn" className="btn btn-ghost" onClick={onCancel} disabled={deleting}>
            Cancel
          </button>
          <button id="confirm-delete-btn" className="btn btn-danger" onClick={onConfirm} disabled={deleting}>
            {deleting ? 'Deleting…' : 'Yes, Delete'}
          </button>
        </div>
      </div>
    </div>
  );
}
