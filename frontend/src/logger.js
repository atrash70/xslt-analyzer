const isDebug = process.env.REACT_APP_DEBUG_LOG === "true";

export const log = (...args) => {
  if (isDebug) console.log("[LOG]", ...args);
};
