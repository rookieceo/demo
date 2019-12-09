<template>
  <v-layout justify-center align-center>
    <v-flex xs12 sm8 md6>
      <div class="text-center"></div>
      <v-card>
        <v-card-title class="headline">수정</v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" xs="12">
                <v-text-field label="게시글번호" v-model="post.postId" disabled></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" xs="12">
                <v-text-field
                  label="제목"
                  v-model="post.title"
                  v-validate="'required'"
                  data-vv-as="[제목]"
                  data-vv-name="title"
                  :error-messages="errors.collect('title')"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" xs="12">
                <v-textarea
                  label="내용"
                  auto-grow
                  v-model="post.content"
                  v-validate="'required'"
                  data-vv-as="[내용]"
                  data-vv-name="content"
                  :error-messages="errors.collect('content')"
                ></v-textarea>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" xs="12">
                <v-text-field
                  label="작성자"
                  v-model="post.createdBy"
                  v-validate="'required'"
                  data-vv-as="[작성자]"
                  data-vv-name="createdBy"
                  :error-messages="errors.collect('createdBy')"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" xs="12">
                <v-text-field label="작성일" v-model="post.createdAt" disabled></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-btn tile color="primary" @click.stop="save">저장</v-btn>
          <v-spacer />
          <v-btn tile color="error" nuxt to="/">취소</v-btn>
        </v-card-actions>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
import moment from 'moment'

export default {
  // Routing 파라미터 유효성 검사
  validate({ params }) {
    // Must be a number
    return /^\d+$/.test(params.postId)
  },
  async asyncData({ $axios, params }) {
    const data = await $axios.$get(`/api/posts/${params.postId}`)
    return {
      post: data
    }
  },
  // 폼 유효성 검사기(vee-validate)
  $_veeValidate: {
    validator: 'new' // give me my own validator scope.
  },
  data() {
    return {
      post: {
        postId: -1,
        createdBy: '',
        content: '',
        title: '',
        createdAt: ''
      }
    }
  },
  created() {
    //  this.getItem();
  },
  mounted() {},
  methods: {
    async getItem() {
      const data = await this.$axios.$get(`/api/posts/${params.postId}`)
      this.post = data
    },
    save() {
      this.$validator.validateAll().then(async result => {
        if (result) {
          try {
            const data = await this.$axios.$put(
              `/api/posts/${this.post.postId}`,
              this.post
            )
            this.$router.push('/')
          } catch (err) {
            throw err
          }
        }
      })
    }
  }
}
</script>
