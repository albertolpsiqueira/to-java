import React, { useEffect, useState } from "react";
import { Container, Typography, Paper, TextField, Button } from "@mui/material";
import axios from "axios";
import { baseURL } from "../api/user";
import { useNavigate, useParams } from "react-router-dom";

interface User {
  name: string;
  email: string;
}

const EditUser: React.FC = () => {
  const { email } = useParams();
  const [form, setForm] = useState<User>({ name: "", email: email || "" });
  const navigate = useNavigate();

  useEffect(() => {
    const fetchUser = async () => {
      const res = await axios.get(`${baseURL}/${email}`);
      setForm(res.data);
    };
    fetchUser();
  }, [email]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await axios.put(`${baseURL}/${email}`, form);
    navigate("/");
  };

  return (
    <Container maxWidth="sm">
      <Typography variant="h4" sx={{ mt: 4, mb: 2 }}>
        Editar Usuário
      </Typography>
      <Paper sx={{ p: 2 }}>
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
    </Container>
  );
};

export default EditUser;
