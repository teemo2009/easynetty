// source: PersonMsg.proto

public final class PersonMsg {
    private PersonMsg() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions(
                (com.google.protobuf.ExtensionRegistryLite) registry);
    }

    public interface PersonOrBuilder extends
            // @@protoc_insertion_point(interface_extends:Person)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>string query = 1;</code>
         */
        String getQuery();

        /**
         * <code>string query = 1;</code>
         */
        com.google.protobuf.ByteString
        getQueryBytes();

        /**
         * <code>int32 page_number = 2;</code>
         */
        int getPageNumber();

        /**
         * <code>int32 result_per_page = 3;</code>
         */
        int getResultPerPage();
    }

    /**
     * Protobuf type {@code Person}
     */
    public static final class Person extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:Person)
            PersonOrBuilder {
        private static final long serialVersionUID = 0L;

        // Use Person.newBuilder() to construct.
        private Person(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Person() {
            query_ = "";
            pageNumber_ = 0;
            resultPerPage_ = 0;
        }

        @Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }

        private Person(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            int mutable_bitField0_ = 0;
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        default: {
                            if (!parseUnknownFieldProto3(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                        case 10: {
                            String s = input.readStringRequireUtf8();

                            query_ = s;
                            break;
                        }
                        case 16: {

                            pageNumber_ = input.readInt32();
                            break;
                        }
                        case 24: {

                            resultPerPage_ = input.readInt32();
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (java.io.IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return PersonMsg.internal_static_Person_descriptor;
        }

        @Override
        protected FieldAccessorTable
        internalGetFieldAccessorTable() {
            return PersonMsg.internal_static_Person_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            Person.class, Builder.class);
        }

        public static final int QUERY_FIELD_NUMBER = 1;
        private volatile Object query_;

        /**
         * <code>string query = 1;</code>
         */
        @Override
        public String getQuery() {
            Object ref = query_;
            if (ref instanceof String) {
                return (String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                String s = bs.toStringUtf8();
                query_ = s;
                return s;
            }
        }

        /**
         * <code>string query = 1;</code>
         */
        @Override
        public com.google.protobuf.ByteString
        getQueryBytes() {
            Object ref = query_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (String) ref);
                query_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int PAGE_NUMBER_FIELD_NUMBER = 2;
        private int pageNumber_;

        /**
         * <code>int32 page_number = 2;</code>
         */
        @Override
        public int getPageNumber() {
            return pageNumber_;
        }

        public static final int RESULT_PER_PAGE_FIELD_NUMBER = 3;
        private int resultPerPage_;

        /**
         * <code>int32 result_per_page = 3;</code>
         */
        @Override
        public int getResultPerPage() {
            return resultPerPage_;
        }

        private byte memoizedIsInitialized = -1;

        @Override
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }

            memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            if (!getQueryBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 1, query_);
            }
            if (pageNumber_ != 0) {
                output.writeInt32(2, pageNumber_);
            }
            if (resultPerPage_ != 0) {
                output.writeInt32(3, resultPerPage_);
            }
            unknownFields.writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) {
                return size;
            }

            size = 0;
            if (!getQueryBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, query_);
            }
            if (pageNumber_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(2, pageNumber_);
            }
            if (resultPerPage_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(3, resultPerPage_);
            }
            size += unknownFields.getSerializedSize();
            memoizedSize = size;
            return size;
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Person)) {
                return super.equals(obj);
            }
            Person other = (Person) obj;

            boolean result = true;
            result = result && getQuery()
                    .equals(other.getQuery());
            result = result && (getPageNumber()
                    == other.getPageNumber());
            result = result && (getResultPerPage()
                    == other.getResultPerPage());
            result = result && unknownFields.equals(other.unknownFields);
            return result;
        }

        @Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptor().hashCode();
            hash = (37 * hash) + QUERY_FIELD_NUMBER;
            hash = (53 * hash) + getQuery().hashCode();
            hash = (37 * hash) + PAGE_NUMBER_FIELD_NUMBER;
            hash = (53 * hash) + getPageNumber();
            hash = (37 * hash) + RESULT_PER_PAGE_FIELD_NUMBER;
            hash = (53 * hash) + getResultPerPage();
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static Person parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Person parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Person parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Person parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Person parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Person parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Person parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static Person parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static Person parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }

        public static Person parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static Person parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static Person parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Person prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        protected Builder newBuilderForType(
                BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /**
         * Protobuf type {@code Person}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:Person)
                PersonOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return PersonMsg.internal_static_Person_descriptor;
            }

            @Override
            protected FieldAccessorTable
            internalGetFieldAccessorTable() {
                return PersonMsg.internal_static_Person_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                Person.class, Builder.class);
            }

            // Construct using PersonMsg.Person.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }

            @Override
            public Builder clear() {
                super.clear();
                query_ = "";

                pageNumber_ = 0;

                resultPerPage_ = 0;

                return this;
            }

            @Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return PersonMsg.internal_static_Person_descriptor;
            }

            @Override
            public Person getDefaultInstanceForType() {
                return Person.getDefaultInstance();
            }

            @Override
            public Person build() {
                Person result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Person buildPartial() {
                Person result = new Person(this);
                result.query_ = query_;
                result.pageNumber_ = pageNumber_;
                result.resultPerPage_ = resultPerPage_;
                onBuilt();
                return result;
            }

            @Override
            public Builder clone() {

                return (Builder) super.clone();
            }

            @Override
            public Builder setField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    Object value) {
                return (Builder) super.setField(field, value);
            }

            @Override
            public Builder clearField(
                    com.google.protobuf.Descriptors.FieldDescriptor field) {
                return (Builder) super.clearField(field);
            }

            @Override
            public Builder clearOneof(
                    com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return (Builder) super.clearOneof(oneof);
            }

            @Override
            public Builder setRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    int index, Object value) {
                return (Builder) super.setRepeatedField(field, index, value);
            }

            @Override
            public Builder addRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    Object value) {
                return (Builder) super.addRepeatedField(field, value);
            }

            @Override
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof Person) {
                    return mergeFrom((Person) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(Person other) {
                if (other == Person.getDefaultInstance()) return this;
                if (!other.getQuery().isEmpty()) {
                    query_ = other.query_;
                    onChanged();
                }
                if (other.getPageNumber() != 0) {
                    setPageNumber(other.getPageNumber());
                }
                if (other.getResultPerPage() != 0) {
                    setResultPerPage(other.getResultPerPage());
                }
                this.mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                Person parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (Person) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private Object query_ = "";

            /**
             * <code>string query = 1;</code>
             */
            @Override
            public String getQuery() {
                Object ref = query_;
                if (!(ref instanceof String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    String s = bs.toStringUtf8();
                    query_ = s;
                    return s;
                } else {
                    return (String) ref;
                }
            }

            /**
             * <code>string query = 1;</code>
             */
            @Override
            public com.google.protobuf.ByteString
            getQueryBytes() {
                Object ref = query_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (String) ref);
                    query_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string query = 1;</code>
             */
            public Builder setQuery(
                    String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                query_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string query = 1;</code>
             */
            public Builder clearQuery() {

                query_ = getDefaultInstance().getQuery();
                onChanged();
                return this;
            }

            /**
             * <code>string query = 1;</code>
             */
            public Builder setQueryBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                query_ = value;
                onChanged();
                return this;
            }

            private int pageNumber_;

            /**
             * <code>int32 page_number = 2;</code>
             */
            @Override
            public int getPageNumber() {
                return pageNumber_;
            }

            /**
             * <code>int32 page_number = 2;</code>
             */
            public Builder setPageNumber(int value) {

                pageNumber_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>int32 page_number = 2;</code>
             */
            public Builder clearPageNumber() {

                pageNumber_ = 0;
                onChanged();
                return this;
            }

            private int resultPerPage_;

            /**
             * <code>int32 result_per_page = 3;</code>
             */
            @Override
            public int getResultPerPage() {
                return resultPerPage_;
            }

            /**
             * <code>int32 result_per_page = 3;</code>
             */
            public Builder setResultPerPage(int value) {

                resultPerPage_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>int32 result_per_page = 3;</code>
             */
            public Builder clearResultPerPage() {

                resultPerPage_ = 0;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.setUnknownFieldsProto3(unknownFields);
            }

            @Override
            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }


            // @@protoc_insertion_point(builder_scope:Person)
        }

        // @@protoc_insertion_point(class_scope:Person)
        private static final Person DEFAULT_INSTANCE;

        static {
            DEFAULT_INSTANCE = new Person();
        }

        public static Person getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<Person>
                PARSER = new com.google.protobuf.AbstractParser<Person>() {
            @Override
            public Person parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new Person(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<Person> parser() {
            return PARSER;
        }

        @Override
        public com.google.protobuf.Parser<Person> getParserForType() {
            return PARSER;
        }

        @Override
        public Person getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_Person_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_Person_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\017PersonMsg.proto\"E\n\006Person\022\r\n\005query\030\001 \001" +
                        "(\t\022\023\n\013page_number\030\002 \001(\005\022\027\n\017result_per_pa" +
                        "ge\030\003 \001(\005b\006proto3"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    @Override
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        }, assigner);
        internal_static_Person_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_Person_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_Person_descriptor,
                new String[]{"Query", "PageNumber", "ResultPerPage",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}
