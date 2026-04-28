/**
 * addressBookApi.js
 * Centralised Axios client for the AddressBook Spring Boot REST API.
 * All API calls go through these helpers so the base URL is a single source of truth.
 */
import axios from 'axios';

// In dev the Vite proxy rewrites /addressbookservice → http://localhost:8080/addressbookservice
const BASE = '/addressbookservice';

const api = axios.create({
  baseURL: BASE,
  headers: { 'Content-Type': 'application/json' },
});

/** Fetch all address book entries */
export const getAll = () => api.get('/');

/** Fetch a single entry by id */
export const getById = (id) => api.get(`/get/${id}`);

/** Create a new entry; payload: { name, phoneNumber } */
export const create = (payload) => api.post('/create', payload);

/** Update an existing entry by id; payload: { name, phoneNumber } */
export const update = (id, payload) => api.put(`/update/${id}`, payload);

/** Delete an entry by id */
export const remove = (id) => api.delete(`/delete/${id}`);
