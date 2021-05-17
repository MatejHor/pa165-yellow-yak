enum Storage {
  AUTH_TOKEN = "authToken",
}

export const load = () => window.sessionStorage.getItem(Storage.AUTH_TOKEN);

export const save = (token: string) => window.sessionStorage.setItem(Storage.AUTH_TOKEN, token);
