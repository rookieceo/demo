export const state = () => ({
  pagination: {
    descending: [true],
    page: 1,
    itemsPerPage: 10,
    sortBy: ['createdAt'],
    totalItems: 0,
    mustSort: true
  }
})

export const mutations = {
  setPagination(state, payload) {
    state.pagination = payload
  },
  setTotalItems(state, payload) {
    state.pagination.totalItems = payload
  },
  setPage(state, payload) {
    state.pagination.page = payload
  },
  setDescending(state, payload) {
    state.pagination.descending[0] = payload
  },
  setSortBy(state, payload) {
    state.pagination.sortBy[0] = payload
  },
  setItemsPerPage(state, payload) {
    state.pagination.setItemsPerPage = payload
  }
}

export const getters = {
  pagination(state) {
    return state.pagination
  }
}

export const actions = {}
