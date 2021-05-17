const OFF = 0;

module.exports = {
  extends: ["@reactizer"],
  rules: {
    eqeqeq: OFF,
    "no-eq-null": OFF,
    "react/jsx-no-bind": OFF,
  },
  overrides: [
    {
      files: ["*.ts", "*.tsx"],
      extends: ["@reactizer/eslint-config/ts"],
      parserOptions: {
        project: "./tsconfig.json",
      },
    },
  ],
};
