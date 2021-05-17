# Gaming Management

This project was bootstrapped with [Create React Index](https://github.com/facebook/create-react-app).

## Development

Make an `.env` file based on `.env.example`.

Scripts:
- `yarn start` for local development
- `yarn build` for production build

CI:

- `yarn ci` runs everything listed below
- `yarn types`
- `yarn lint`
- `yarn prettier`

## Structure

* `/components` reusable components
* `/pages` individual sections of the application, can contain private components
* `/records` types describing data structures
* `/services` services that operate with data or provide utility

Follow established conventions. Make `.css` file next to `.tsx` file if custom CSS
is needed.

### API

Use `useSWR` for `GET` requests, and the `fetch` service for other methods.

## Docs

- CSS [Bootstrap](https://react-bootstrap.github.io/getting-started/)
- Routing [Wouter](https://github.com/molefrog/wouter)
- Data [SWR](https://swr.vercel.app/)
