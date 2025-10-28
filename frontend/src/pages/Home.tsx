import React, { useEffect, useState } from "react";
import {
  Container,
  Typography,
  Box,
  Paper,
  TextField,
  Button,
  IconButton,
} from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import axios from "axios";
import { baseURL } from "../api/user";
import { useNavigate } from "react-router-dom";

interface User {
  _id: string;
  name: string;
  email: string;
}

const Home: React.FC = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [form, setForm] = useState<Partial<User>>({ name: "", email: "" });
  const navigate = useNavigate();

  const fetchUsers = async () => {
    const res = await axios.get(baseURL);
    setUsers(res.data);
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await axios.post(baseURL, form);
    setForm({ name: "", email: "" });
    fetchUsers();
  };

  const handleDelete = async (id: string) => {
    await axios.delete(`${baseURL}/${id}`);
    fetchUsers();
  };

  return (
    <Container maxWidth="sm">
      <Typography variant="h4" sx={{ mt: 4, mb: 2 }}>
        Usuários
      </Typography>
      <Paper sx={{ p: 2, mb: 4 }}>
        <form onSubmit={handleSubmit}>
          <TextField
            label="Nome"
            name="name"
            value={form.name}
            onChange={handleChange}
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="Email"
            name="email"
            value={form.email}
            onChange={handleChange}
            fullWidth
            margin="normal"
            required
          />
          <Button type="submit" variant="contained" color="primary">
            Salvar
          </Button>
        </form>
      </Paper>
      <Paper sx={{ p: 2 }}>
        <Typography variant="h6" sx={{ mb: 2 }}>
          Lista de Usuários
        </Typography>
        {users.map((user) => (
          <Box
            key={user.email}
            display="flex"
            alignItems="center"
            justifyContent="space-between"
            mb={2}
          >
            <Box>
              <Typography>{user.name}</Typography>
              <Typography color="text.secondary">{user.email}</Typography>
            </Box>
            <Box>
              <IconButton
                color="primary"
                onClick={() => navigate(`/edit/${user._id}`)}
              >
                <EditIcon />
              </IconButton>
              <IconButton color="error" onClick={() => handleDelete(user._id)}>
                <DeleteIcon />
              </IconButton>
            </Box>
          </Box>
        ))}
      </Paper>
    </Container>
  );
};

export default Home;
