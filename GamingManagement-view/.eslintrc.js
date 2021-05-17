const OFF = 0;

module.exports = {
  extends: ["@reactizer"],
  rules: {
    eqeqeq: OFF,
    "no-eq-null": OFF,
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
